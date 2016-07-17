package com.kenai.jbosh;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class BOSHClient
{
  private static final boolean ASSERTIONS = false;
  private static final int DEFAULT_EMPTY_REQUEST_DELAY = 100;
  private static final int DEFAULT_PAUSE_MARGIN = 500;
  private static final int EMPTY_REQUEST_DELAY = 0;
  private static final String ERROR = "error";
  private static final String INTERRUPTED = "Interrupted";
  private static final Logger LOG;
  private static final String NULL_LISTENER = "Listener may not be null";
  private static final int PAUSE_MARGIN = 0;
  private static final String TERMINATE = "terminate";
  private static final String UNHANDLED = "Unhandled Exception";
  private final BOSHClientConfig cfg;
  private CMSessionParams cmParams;
  private final Set<BOSHClientConnListener> connListeners = new CopyOnWriteArraySet();
  private final Condition drained = this.lock.newCondition();
  private ScheduledFuture emptyRequestFuture;
  private final Runnable emptyRequestRunnable = new Runnable()
  {
    public void run()
    {
      BOSHClient.this.sendEmptyRequest();
    }
  };
  private final AtomicReference<ExchangeInterceptor> exchInterceptor = new AtomicReference();
  private Queue<HTTPExchange> exchanges = new LinkedList();
  private final HTTPSender httpSender = new ApacheHTTPSender();
  private final ReentrantLock lock = new ReentrantLock();
  private final Condition notEmpty = this.lock.newCondition();
  private final Condition notFull = this.lock.newCondition();
  private List<ComposableBody> pendingRequestAcks = new ArrayList();
  private SortedSet<Long> pendingResponseAcks = new TreeSet();
  private final Runnable procRunnable = new Runnable()
  {
    public void run()
    {
      BOSHClient.this.processMessages();
    }
  };
  private Thread procThread;
  private final RequestIDSequence requestIDSeq = new RequestIDSequence();
  private final Set<BOSHClientRequestListener> requestListeners = new CopyOnWriteArraySet();
  private Long responseAck = Long.valueOf(-1L);
  private final Set<BOSHClientResponseListener> responseListeners = new CopyOnWriteArraySet();
  private final ScheduledExecutorService schedExec = Executors.newSingleThreadScheduledExecutor();

  static
  {
    boolean bool1 = true;
    boolean bool2;
    String str;
    if (!BOSHClient.class.desiredAssertionStatus())
    {
      bool2 = bool1;
      $assertionsDisabled = bool2;
      LOG = Logger.getLogger(BOSHClient.class.getName());
      EMPTY_REQUEST_DELAY = Integer.getInteger(BOSHClient.class.getName() + ".emptyRequestDelay", 100).intValue();
      PAUSE_MARGIN = Integer.getInteger(BOSHClient.class.getName() + ".pauseMargin", 500).intValue();
      str = BOSHClient.class.getSimpleName() + ".assertionsEnabled";
      if (System.getProperty(str) != null)
        break label143;
      if ($assertionsDisabled)
        break label151;
    }
    while (true)
    {
      ASSERTIONS = bool1;
      return;
      bool2 = false;
      break;
      label143: bool1 = Boolean.getBoolean(str);
      continue;
      label151: bool1 = false;
    }
  }

  private BOSHClient(BOSHClientConfig paramBOSHClientConfig)
  {
    this.cfg = paramBOSHClientConfig;
    init();
  }

  private void applyFrom(ComposableBody.Builder paramBuilder)
  {
    assertLocked();
    String str = this.cfg.getFrom();
    if (str != null)
      paramBuilder.setAttribute(Attributes.FROM, str);
  }

  private void applyResponseAcknowledgement(ComposableBody.Builder paramBuilder, long paramLong)
  {
    assertLocked();
    if (this.responseAck.equals(Long.valueOf(-1L)));
    Long localLong;
    do
    {
      return;
      localLong = Long.valueOf(paramLong - 1L);
    }
    while (this.responseAck.equals(localLong));
    paramBuilder.setAttribute(Attributes.ACK, this.responseAck.toString());
  }

  private void applyRoute(ComposableBody.Builder paramBuilder)
  {
    assertLocked();
    String str = this.cfg.getRoute();
    if (str != null)
      paramBuilder.setAttribute(Attributes.ROUTE, str);
  }

  private ComposableBody applySessionCreationRequest(long paramLong, ComposableBody paramComposableBody)
    throws BOSHException
  {
    assertLocked();
    ComposableBody.Builder localBuilder = paramComposableBody.rebuild();
    localBuilder.setAttribute(Attributes.TO, this.cfg.getTo());
    localBuilder.setAttribute(Attributes.XML_LANG, this.cfg.getLang());
    localBuilder.setAttribute(Attributes.VER, AttrVersion.getSupportedVersion().toString());
    localBuilder.setAttribute(Attributes.WAIT, "60");
    localBuilder.setAttribute(Attributes.HOLD, "1");
    localBuilder.setAttribute(Attributes.RID, Long.toString(paramLong));
    applyRoute(localBuilder);
    applyFrom(localBuilder);
    localBuilder.setAttribute(Attributes.ACK, "1");
    localBuilder.setAttribute(Attributes.SID, null);
    return localBuilder.build();
  }

  private ComposableBody applySessionData(long paramLong, ComposableBody paramComposableBody)
    throws BOSHException
  {
    assertLocked();
    ComposableBody.Builder localBuilder = paramComposableBody.rebuild();
    localBuilder.setAttribute(Attributes.SID, this.cmParams.getSessionID().toString());
    localBuilder.setAttribute(Attributes.RID, Long.toString(paramLong));
    applyResponseAcknowledgement(localBuilder, paramLong);
    return localBuilder.build();
  }

  private void assertLocked()
  {
    if ((ASSERTIONS) && (!this.lock.isHeldByCurrentThread()))
      throw new AssertionError("Lock is not held by current thread");
  }

  private void assertUnlocked()
  {
    if ((ASSERTIONS) && (this.lock.isHeldByCurrentThread()))
      throw new AssertionError("Lock is held by current thread");
  }

  private void blockUntilSendable(AbstractBody paramAbstractBody)
  {
    assertLocked();
    while ((isWorking()) && (!isImmediatelySendable(paramAbstractBody)))
      try
      {
        this.notFull.await();
      }
      catch (InterruptedException localInterruptedException)
      {
        LOG.log(Level.FINEST, "Interrupted", localInterruptedException);
      }
  }

  private void checkForTerminalBindingConditions(AbstractBody paramAbstractBody, int paramInt)
    throws BOSHException
  {
    TerminalBindingCondition localTerminalBindingCondition = getTerminalBindingCondition(paramInt, paramAbstractBody);
    if (localTerminalBindingCondition != null)
      throw new BOSHException("Terminal binding condition encountered: " + localTerminalBindingCondition.getCondition() + "  (" + localTerminalBindingCondition.getMessage() + ")");
  }

  private void clearEmptyRequest()
  {
    assertLocked();
    if (this.emptyRequestFuture != null)
    {
      this.emptyRequestFuture.cancel(false);
      this.emptyRequestFuture = null;
    }
  }

  public static BOSHClient create(BOSHClientConfig paramBOSHClientConfig)
  {
    if (paramBOSHClientConfig == null)
      throw new IllegalArgumentException("Client configuration may not be null");
    return new BOSHClient(paramBOSHClientConfig);
  }

  // ERROR //
  private void dispose(Throwable paramThrowable)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 443	com/kenai/jbosh/BOSHClient:assertUnlocked	()V
    //   4: aload_0
    //   5: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   8: invokevirtual 445	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   11: aload_0
    //   12: getfield 447	com/kenai/jbosh/BOSHClient:procThread	Ljava/lang/Thread;
    //   15: astore_3
    //   16: aload_3
    //   17: ifnonnull +11 -> 28
    //   20: aload_0
    //   21: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   24: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   27: return
    //   28: aload_0
    //   29: aconst_null
    //   30: putfield 447	com/kenai/jbosh/BOSHClient:procThread	Ljava/lang/Thread;
    //   33: aload_0
    //   34: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   37: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   40: aload_1
    //   41: ifnonnull +102 -> 143
    //   44: aload_0
    //   45: invokespecial 453	com/kenai/jbosh/BOSHClient:fireConnectionClosed	()V
    //   48: aload_0
    //   49: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   52: invokevirtual 445	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   55: aload_0
    //   56: invokespecial 455	com/kenai/jbosh/BOSHClient:clearEmptyRequest	()V
    //   59: aload_0
    //   60: aconst_null
    //   61: putfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   64: aload_0
    //   65: aconst_null
    //   66: putfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   69: aload_0
    //   70: aconst_null
    //   71: putfield 219	com/kenai/jbosh/BOSHClient:pendingResponseAcks	Ljava/util/SortedSet;
    //   74: aload_0
    //   75: aconst_null
    //   76: putfield 234	com/kenai/jbosh/BOSHClient:pendingRequestAcks	Ljava/util/List;
    //   79: aload_0
    //   80: getfield 170	com/kenai/jbosh/BOSHClient:notEmpty	Ljava/util/concurrent/locks/Condition;
    //   83: invokeinterface 458 1 0
    //   88: aload_0
    //   89: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   92: invokeinterface 458 1 0
    //   97: aload_0
    //   98: getfield 174	com/kenai/jbosh/BOSHClient:drained	Ljava/util/concurrent/locks/Condition;
    //   101: invokeinterface 458 1 0
    //   106: aload_0
    //   107: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   110: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   113: aload_0
    //   114: getfield 191	com/kenai/jbosh/BOSHClient:httpSender	Lcom/kenai/jbosh/HTTPSender;
    //   117: invokeinterface 463 1 0
    //   122: aload_0
    //   123: getfield 209	com/kenai/jbosh/BOSHClient:schedExec	Ljava/util/concurrent/ScheduledExecutorService;
    //   126: invokeinterface 469 1 0
    //   131: pop
    //   132: return
    //   133: astore_2
    //   134: aload_0
    //   135: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   138: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   141: aload_2
    //   142: athrow
    //   143: aload_0
    //   144: aload_1
    //   145: invokespecial 472	com/kenai/jbosh/BOSHClient:fireConnectionClosedOnError	(Ljava/lang/Throwable;)V
    //   148: goto -100 -> 48
    //   151: astore 4
    //   153: aload_0
    //   154: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   157: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   160: aload 4
    //   162: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   11	16	133	finally
    //   28	33	133	finally
    //   55	106	151	finally
  }

  private void fireConnectionClosed()
  {
    assertUnlocked();
    Iterator localIterator = this.connListeners.iterator();
    BOSHClientConnEvent localBOSHClientConnEvent = null;
    while (localIterator.hasNext())
    {
      BOSHClientConnListener localBOSHClientConnListener = (BOSHClientConnListener)localIterator.next();
      if (localBOSHClientConnEvent == null)
        localBOSHClientConnEvent = BOSHClientConnEvent.createConnectionClosedEvent(this);
      try
      {
        localBOSHClientConnListener.connectionEvent(localBOSHClientConnEvent);
      }
      catch (Exception localException)
      {
        LOG.log(Level.WARNING, "Unhandled Exception", localException);
      }
    }
  }

  private void fireConnectionClosedOnError(Throwable paramThrowable)
  {
    assertUnlocked();
    Iterator localIterator = this.connListeners.iterator();
    BOSHClientConnEvent localBOSHClientConnEvent = null;
    while (localIterator.hasNext())
    {
      BOSHClientConnListener localBOSHClientConnListener = (BOSHClientConnListener)localIterator.next();
      if (localBOSHClientConnEvent == null)
        localBOSHClientConnEvent = BOSHClientConnEvent.createConnectionClosedOnErrorEvent(this, this.pendingRequestAcks, paramThrowable);
      try
      {
        localBOSHClientConnListener.connectionEvent(localBOSHClientConnEvent);
      }
      catch (Exception localException)
      {
        LOG.log(Level.WARNING, "Unhandled Exception", localException);
      }
    }
  }

  private void fireConnectionEstablished()
  {
    boolean bool = this.lock.isHeldByCurrentThread();
    if (bool)
      this.lock.unlock();
    try
    {
      Iterator localIterator = this.connListeners.iterator();
      Object localObject2 = null;
      while (localIterator.hasNext())
      {
        BOSHClientConnListener localBOSHClientConnListener = (BOSHClientConnListener)localIterator.next();
        if (localObject2 == null)
        {
          BOSHClientConnEvent localBOSHClientConnEvent = BOSHClientConnEvent.createConnectionEstablishedEvent(this);
          localObject2 = localBOSHClientConnEvent;
        }
        try
        {
          localBOSHClientConnListener.connectionEvent(localObject2);
        }
        catch (Exception localException)
        {
          LOG.log(Level.WARNING, "Unhandled Exception", localException);
        }
      }
    }
    finally
    {
      if (bool)
        this.lock.lock();
    }
    if (bool)
      this.lock.lock();
  }

  private void fireRequestSent(AbstractBody paramAbstractBody)
  {
    assertUnlocked();
    Iterator localIterator = this.requestListeners.iterator();
    BOSHMessageEvent localBOSHMessageEvent = null;
    while (localIterator.hasNext())
    {
      BOSHClientRequestListener localBOSHClientRequestListener = (BOSHClientRequestListener)localIterator.next();
      if (localBOSHMessageEvent == null)
        localBOSHMessageEvent = BOSHMessageEvent.createRequestSentEvent(this, paramAbstractBody);
      try
      {
        localBOSHClientRequestListener.requestSent(localBOSHMessageEvent);
      }
      catch (Exception localException)
      {
        LOG.log(Level.WARNING, "Unhandled Exception", localException);
      }
    }
  }

  private void fireResponseReceived(AbstractBody paramAbstractBody)
  {
    assertUnlocked();
    Iterator localIterator = this.responseListeners.iterator();
    BOSHMessageEvent localBOSHMessageEvent = null;
    while (localIterator.hasNext())
    {
      BOSHClientResponseListener localBOSHClientResponseListener = (BOSHClientResponseListener)localIterator.next();
      if (localBOSHMessageEvent == null)
        localBOSHMessageEvent = BOSHMessageEvent.createResponseReceivedEvent(this, paramAbstractBody);
      try
      {
        localBOSHClientResponseListener.responseReceived(localBOSHMessageEvent);
      }
      catch (Exception localException)
      {
        LOG.log(Level.WARNING, "Unhandled Exception", localException);
      }
    }
  }

  private long getDefaultEmptyRequestDelay()
  {
    assertLocked();
    AttrPolling localAttrPolling = this.cmParams.getPollingInterval();
    if (localAttrPolling == null)
      return EMPTY_REQUEST_DELAY;
    return localAttrPolling.getInMilliseconds();
  }

  private TerminalBindingCondition getTerminalBindingCondition(int paramInt, AbstractBody paramAbstractBody)
  {
    assertLocked();
    if (isTermination(paramAbstractBody))
      return TerminalBindingCondition.forString(paramAbstractBody.getAttribute(Attributes.CONDITION));
    if ((this.cmParams != null) && (this.cmParams.getVersion() == null))
      return TerminalBindingCondition.forHTTPResponseCode(paramInt);
    return null;
  }

  private void init()
  {
    assertUnlocked();
    this.lock.lock();
    try
    {
      this.httpSender.init(this.cfg);
      this.procThread = new Thread(this.procRunnable);
      this.procThread.setDaemon(true);
      this.procThread.setName(BOSHClient.class.getSimpleName() + "[" + System.identityHashCode(this) + "]: Receive thread");
      this.procThread.start();
      return;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  private boolean isImmediatelySendable(AbstractBody paramAbstractBody)
  {
    boolean bool = true;
    assertLocked();
    if (this.cmParams == null)
      bool = this.exchanges.isEmpty();
    int i;
    do
    {
      AttrRequests localAttrRequests;
      do
      {
        return bool;
        localAttrRequests = this.cmParams.getRequests();
      }
      while (localAttrRequests == null);
      i = localAttrRequests.intValue();
    }
    while ((this.exchanges.size() < i) || ((this.exchanges.size() == i) && ((isTermination(paramAbstractBody)) || (isPause(paramAbstractBody)))));
    return false;
  }

  private static boolean isPause(AbstractBody paramAbstractBody)
  {
    return paramAbstractBody.getAttribute(Attributes.PAUSE) != null;
  }

  private static boolean isRecoverableBindingCondition(AbstractBody paramAbstractBody)
  {
    return "error".equals(paramAbstractBody.getAttribute(Attributes.TYPE));
  }

  private static boolean isTermination(AbstractBody paramAbstractBody)
  {
    return "terminate".equals(paramAbstractBody.getAttribute(Attributes.TYPE));
  }

  private boolean isWorking()
  {
    assertLocked();
    return this.procThread != null;
  }

  private HTTPExchange nextExchange()
  {
    assertUnlocked();
    Thread localThread = Thread.currentThread();
    HTTPExchange localHTTPExchange = null;
    this.lock.lock();
    try
    {
      while (true)
      {
        boolean bool = localThread.equals(this.procThread);
        if (!bool)
          return localHTTPExchange;
        localHTTPExchange = (HTTPExchange)this.exchanges.peek();
        if (localHTTPExchange == null);
        try
        {
          this.notEmpty.await();
          if (localHTTPExchange == null)
            continue;
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            LOG.log(Level.FINEST, "Interrupted", localInterruptedException);
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
  }

  // ERROR //
  private void processExchange(HTTPExchange paramHTTPExchange)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 443	com/kenai/jbosh/BOSHClient:assertUnlocked	()V
    //   4: aload_1
    //   5: invokevirtual 642	com/kenai/jbosh/HTTPExchange:getHTTPResponse	()Lcom/kenai/jbosh/HTTPResponse;
    //   8: astore 4
    //   10: aload 4
    //   12: invokeinterface 648 1 0
    //   17: astore 5
    //   19: aload 4
    //   21: invokeinterface 651 1 0
    //   26: istore 6
    //   28: aload_0
    //   29: aload 5
    //   31: invokespecial 653	com/kenai/jbosh/BOSHClient:fireResponseReceived	(Lcom/kenai/jbosh/AbstractBody;)V
    //   34: aload_1
    //   35: invokevirtual 656	com/kenai/jbosh/HTTPExchange:getRequest	()Lcom/kenai/jbosh/AbstractBody;
    //   38: astore 7
    //   40: aload_0
    //   41: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   44: invokevirtual 445	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   47: aload_0
    //   48: invokespecial 379	com/kenai/jbosh/BOSHClient:isWorking	()Z
    //   51: ifne +121 -> 172
    //   54: aload_0
    //   55: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   58: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   61: aload_0
    //   62: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   65: invokevirtual 362	java/util/concurrent/locks/ReentrantLock:isHeldByCurrentThread	()Z
    //   68: ifeq +52 -> 120
    //   71: aload_0
    //   72: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   75: aload_1
    //   76: invokeinterface 659 2 0
    //   81: pop
    //   82: aload_0
    //   83: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   86: invokeinterface 601 1 0
    //   91: ifeq +13 -> 104
    //   94: aload_0
    //   95: aload_0
    //   96: aload 7
    //   98: invokespecial 663	com/kenai/jbosh/BOSHClient:processPauseRequest	(Lcom/kenai/jbosh/AbstractBody;)J
    //   101: invokespecial 667	com/kenai/jbosh/BOSHClient:scheduleEmptyRequest	(J)V
    //   104: aload_0
    //   105: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   108: invokeinterface 458 1 0
    //   113: aload_0
    //   114: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   117: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   120: return
    //   121: astore_3
    //   122: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   125: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   128: ldc_w 669
    //   131: aload_3
    //   132: invokevirtual 398	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   135: aload_0
    //   136: aload_3
    //   137: invokespecial 671	com/kenai/jbosh/BOSHClient:dispose	(Ljava/lang/Throwable;)V
    //   140: return
    //   141: astore_2
    //   142: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   145: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   148: ldc 21
    //   150: aload_2
    //   151: invokevirtual 398	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   154: aload_0
    //   155: aload_2
    //   156: invokespecial 671	com/kenai/jbosh/BOSHClient:dispose	(Ljava/lang/Throwable;)V
    //   159: return
    //   160: astore 31
    //   162: aload_0
    //   163: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   166: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   169: aload 31
    //   171: athrow
    //   172: aload_0
    //   173: getfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   176: ifnonnull +18 -> 194
    //   179: aload_0
    //   180: aload 7
    //   182: aload 5
    //   184: invokestatic 675	com/kenai/jbosh/CMSessionParams:fromSessionInit	(Lcom/kenai/jbosh/AbstractBody;Lcom/kenai/jbosh/AbstractBody;)Lcom/kenai/jbosh/CMSessionParams;
    //   187: putfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   190: aload_0
    //   191: invokespecial 677	com/kenai/jbosh/BOSHClient:fireConnectionEstablished	()V
    //   194: aload_0
    //   195: getfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   198: astore 14
    //   200: aload_0
    //   201: aload 5
    //   203: iload 6
    //   205: invokespecial 679	com/kenai/jbosh/BOSHClient:checkForTerminalBindingConditions	(Lcom/kenai/jbosh/AbstractBody;I)V
    //   208: aload 5
    //   210: invokestatic 548	com/kenai/jbosh/BOSHClient:isTermination	(Lcom/kenai/jbosh/AbstractBody;)Z
    //   213: ifeq +87 -> 300
    //   216: aload_0
    //   217: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   220: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   223: aload_0
    //   224: aconst_null
    //   225: invokespecial 671	com/kenai/jbosh/BOSHClient:dispose	(Ljava/lang/Throwable;)V
    //   228: aload_0
    //   229: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   232: invokevirtual 362	java/util/concurrent/locks/ReentrantLock:isHeldByCurrentThread	()Z
    //   235: ifeq -115 -> 120
    //   238: aload_0
    //   239: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   242: aload_1
    //   243: invokeinterface 659 2 0
    //   248: pop
    //   249: aload_0
    //   250: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   253: invokeinterface 601 1 0
    //   258: ifeq +13 -> 271
    //   261: aload_0
    //   262: aload_0
    //   263: aload 7
    //   265: invokespecial 663	com/kenai/jbosh/BOSHClient:processPauseRequest	(Lcom/kenai/jbosh/AbstractBody;)J
    //   268: invokespecial 667	com/kenai/jbosh/BOSHClient:scheduleEmptyRequest	(J)V
    //   271: aload_0
    //   272: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   275: invokeinterface 458 1 0
    //   280: aload_0
    //   281: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   284: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   287: return
    //   288: astore 29
    //   290: aload_0
    //   291: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   294: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   297: aload 29
    //   299: athrow
    //   300: aload 5
    //   302: invokestatic 681	com/kenai/jbosh/BOSHClient:isRecoverableBindingCondition	(Lcom/kenai/jbosh/AbstractBody;)Z
    //   305: ifeq +408 -> 713
    //   308: iconst_0
    //   309: ifne +512 -> 821
    //   312: new 231	java/util/ArrayList
    //   315: dup
    //   316: aload_0
    //   317: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   320: invokeinterface 611 1 0
    //   325: invokespecial 684	java/util/ArrayList:<init>	(I)V
    //   328: astore 15
    //   330: aload_0
    //   331: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   334: invokeinterface 685 1 0
    //   339: astore 16
    //   341: aload 16
    //   343: invokeinterface 485 1 0
    //   348: ifeq +123 -> 471
    //   351: aload 15
    //   353: new 636	com/kenai/jbosh/HTTPExchange
    //   356: dup
    //   357: aload 16
    //   359: invokeinterface 489 1 0
    //   364: checkcast 636	com/kenai/jbosh/HTTPExchange
    //   367: invokevirtual 656	com/kenai/jbosh/HTTPExchange:getRequest	()Lcom/kenai/jbosh/AbstractBody;
    //   370: invokespecial 687	com/kenai/jbosh/HTTPExchange:<init>	(Lcom/kenai/jbosh/AbstractBody;)V
    //   373: invokeinterface 692 2 0
    //   378: pop
    //   379: goto -38 -> 341
    //   382: astore 11
    //   384: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   387: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   390: ldc_w 694
    //   393: aload 11
    //   395: invokevirtual 398	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   398: aload_0
    //   399: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   402: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   405: aload_0
    //   406: aload 11
    //   408: invokespecial 671	com/kenai/jbosh/BOSHClient:dispose	(Ljava/lang/Throwable;)V
    //   411: aload_0
    //   412: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   415: invokevirtual 362	java/util/concurrent/locks/ReentrantLock:isHeldByCurrentThread	()Z
    //   418: ifeq -298 -> 120
    //   421: aload_0
    //   422: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   425: aload_1
    //   426: invokeinterface 659 2 0
    //   431: pop
    //   432: aload_0
    //   433: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   436: invokeinterface 601 1 0
    //   441: ifeq +13 -> 454
    //   444: aload_0
    //   445: aload_0
    //   446: aload 7
    //   448: invokespecial 663	com/kenai/jbosh/BOSHClient:processPauseRequest	(Lcom/kenai/jbosh/AbstractBody;)J
    //   451: invokespecial 667	com/kenai/jbosh/BOSHClient:scheduleEmptyRequest	(J)V
    //   454: aload_0
    //   455: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   458: invokeinterface 458 1 0
    //   463: aload_0
    //   464: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   467: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   470: return
    //   471: aload 15
    //   473: invokeinterface 695 1 0
    //   478: astore 17
    //   480: aload 17
    //   482: invokeinterface 485 1 0
    //   487: ifeq +94 -> 581
    //   490: aload 17
    //   492: invokeinterface 489 1 0
    //   497: checkcast 636	com/kenai/jbosh/HTTPExchange
    //   500: astore 23
    //   502: aload_0
    //   503: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   506: aload 23
    //   508: invokeinterface 696 2 0
    //   513: pop
    //   514: goto -34 -> 480
    //   517: astore 8
    //   519: aload_0
    //   520: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   523: invokevirtual 362	java/util/concurrent/locks/ReentrantLock:isHeldByCurrentThread	()Z
    //   526: ifeq +52 -> 578
    //   529: aload_0
    //   530: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   533: aload_1
    //   534: invokeinterface 659 2 0
    //   539: pop
    //   540: aload_0
    //   541: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   544: invokeinterface 601 1 0
    //   549: ifeq +13 -> 562
    //   552: aload_0
    //   553: aload_0
    //   554: aload 7
    //   556: invokespecial 663	com/kenai/jbosh/BOSHClient:processPauseRequest	(Lcom/kenai/jbosh/AbstractBody;)J
    //   559: invokespecial 667	com/kenai/jbosh/BOSHClient:scheduleEmptyRequest	(J)V
    //   562: aload_0
    //   563: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   566: invokeinterface 458 1 0
    //   571: aload_0
    //   572: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   575: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   578: aload 8
    //   580: athrow
    //   581: aload 15
    //   583: astore 18
    //   585: aload_0
    //   586: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   589: invokevirtual 362	java/util/concurrent/locks/ReentrantLock:isHeldByCurrentThread	()Z
    //   592: ifeq +52 -> 644
    //   595: aload_0
    //   596: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   599: aload_1
    //   600: invokeinterface 659 2 0
    //   605: pop
    //   606: aload_0
    //   607: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   610: invokeinterface 601 1 0
    //   615: ifeq +13 -> 628
    //   618: aload_0
    //   619: aload_0
    //   620: aload 7
    //   622: invokespecial 663	com/kenai/jbosh/BOSHClient:processPauseRequest	(Lcom/kenai/jbosh/AbstractBody;)J
    //   625: invokespecial 667	com/kenai/jbosh/BOSHClient:scheduleEmptyRequest	(J)V
    //   628: aload_0
    //   629: getfield 172	com/kenai/jbosh/BOSHClient:notFull	Ljava/util/concurrent/locks/Condition;
    //   632: invokeinterface 458 1 0
    //   637: aload_0
    //   638: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   641: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   644: aload 18
    //   646: ifnull -526 -> 120
    //   649: aload 18
    //   651: invokeinterface 695 1 0
    //   656: astore 19
    //   658: aload 19
    //   660: invokeinterface 485 1 0
    //   665: ifeq -545 -> 120
    //   668: aload 19
    //   670: invokeinterface 489 1 0
    //   675: checkcast 636	com/kenai/jbosh/HTTPExchange
    //   678: astore 20
    //   680: aload 20
    //   682: aload_0
    //   683: getfield 191	com/kenai/jbosh/BOSHClient:httpSender	Lcom/kenai/jbosh/HTTPSender;
    //   686: aload 14
    //   688: aload 20
    //   690: invokevirtual 656	com/kenai/jbosh/HTTPExchange:getRequest	()Lcom/kenai/jbosh/AbstractBody;
    //   693: invokeinterface 700 3 0
    //   698: invokevirtual 704	com/kenai/jbosh/HTTPExchange:setHTTPResponse	(Lcom/kenai/jbosh/HTTPResponse;)V
    //   701: aload_0
    //   702: aload 20
    //   704: invokevirtual 656	com/kenai/jbosh/HTTPExchange:getRequest	()Lcom/kenai/jbosh/AbstractBody;
    //   707: invokespecial 706	com/kenai/jbosh/BOSHClient:fireRequestSent	(Lcom/kenai/jbosh/AbstractBody;)V
    //   710: goto -52 -> 658
    //   713: aload_0
    //   714: aload 7
    //   716: aload 5
    //   718: invokespecial 710	com/kenai/jbosh/BOSHClient:processRequestAcknowledgements	(Lcom/kenai/jbosh/AbstractBody;Lcom/kenai/jbosh/AbstractBody;)V
    //   721: aload_0
    //   722: aload 7
    //   724: invokespecial 713	com/kenai/jbosh/BOSHClient:processResponseAcknowledgementData	(Lcom/kenai/jbosh/AbstractBody;)V
    //   727: aload_0
    //   728: aload 5
    //   730: invokespecial 717	com/kenai/jbosh/BOSHClient:processResponseAcknowledgementReport	(Lcom/kenai/jbosh/AbstractBody;)Lcom/kenai/jbosh/HTTPExchange;
    //   733: astore 26
    //   735: aconst_null
    //   736: astore 18
    //   738: aload 26
    //   740: ifnull -155 -> 585
    //   743: aconst_null
    //   744: astore 18
    //   746: iconst_0
    //   747: ifne -162 -> 585
    //   750: new 231	java/util/ArrayList
    //   753: dup
    //   754: iconst_1
    //   755: invokespecial 684	java/util/ArrayList:<init>	(I)V
    //   758: astore 18
    //   760: aload 18
    //   762: aload 26
    //   764: invokeinterface 692 2 0
    //   769: pop
    //   770: aload_0
    //   771: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   774: aload 26
    //   776: invokeinterface 696 2 0
    //   781: pop
    //   782: goto -197 -> 585
    //   785: astore 21
    //   787: aload_0
    //   788: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   791: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   794: aload 21
    //   796: athrow
    //   797: astore 12
    //   799: aload_0
    //   800: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   803: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   806: aload 12
    //   808: athrow
    //   809: astore 9
    //   811: aload_0
    //   812: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   815: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   818: aload 9
    //   820: athrow
    //   821: aconst_null
    //   822: astore 15
    //   824: goto -494 -> 330
    //
    // Exception table:
    //   from	to	target	type
    //   4	28	121	com/kenai/jbosh/BOSHException
    //   4	28	141	java/lang/InterruptedException
    //   71	104	160	finally
    //   104	113	160	finally
    //   238	271	288	finally
    //   271	280	288	finally
    //   47	61	382	com/kenai/jbosh/BOSHException
    //   172	194	382	com/kenai/jbosh/BOSHException
    //   194	228	382	com/kenai/jbosh/BOSHException
    //   300	308	382	com/kenai/jbosh/BOSHException
    //   312	330	382	com/kenai/jbosh/BOSHException
    //   330	341	382	com/kenai/jbosh/BOSHException
    //   341	379	382	com/kenai/jbosh/BOSHException
    //   471	480	382	com/kenai/jbosh/BOSHException
    //   480	514	382	com/kenai/jbosh/BOSHException
    //   713	735	382	com/kenai/jbosh/BOSHException
    //   750	782	382	com/kenai/jbosh/BOSHException
    //   47	61	517	finally
    //   172	194	517	finally
    //   194	228	517	finally
    //   300	308	517	finally
    //   312	330	517	finally
    //   330	341	517	finally
    //   341	379	517	finally
    //   384	411	517	finally
    //   471	480	517	finally
    //   480	514	517	finally
    //   713	735	517	finally
    //   750	782	517	finally
    //   595	628	785	finally
    //   628	637	785	finally
    //   421	454	797	finally
    //   454	463	797	finally
    //   529	562	809	finally
    //   562	571	809	finally
  }

  // ERROR //
  private void processMessages()
  {
    // Byte code:
    //   0: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   3: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   6: ldc_w 719
    //   9: invokevirtual 722	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   12: aload_0
    //   13: invokespecial 724	com/kenai/jbosh/BOSHClient:nextExchange	()Lcom/kenai/jbosh/HTTPExchange;
    //   16: astore_2
    //   17: aload_2
    //   18: ifnonnull +16 -> 34
    //   21: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   24: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   27: ldc_w 726
    //   30: invokevirtual 722	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   33: return
    //   34: aload_0
    //   35: getfield 196	com/kenai/jbosh/BOSHClient:exchInterceptor	Ljava/util/concurrent/atomic/AtomicReference;
    //   38: invokevirtual 729	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   41: checkcast 731	com/kenai/jbosh/BOSHClient$ExchangeInterceptor
    //   44: astore_3
    //   45: aload_3
    //   46: ifnull +117 -> 163
    //   49: aload_3
    //   50: aload_2
    //   51: invokevirtual 735	com/kenai/jbosh/BOSHClient$ExchangeInterceptor:interceptExchange	(Lcom/kenai/jbosh/HTTPExchange;)Lcom/kenai/jbosh/HTTPExchange;
    //   54: astore 4
    //   56: aload 4
    //   58: ifnonnull +96 -> 154
    //   61: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   64: getstatic 738	java/util/logging/Level:FINE	Ljava/util/logging/Level;
    //   67: new 101	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 104	java/lang/StringBuilder:<init>	()V
    //   74: ldc_w 740
    //   77: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: aload_2
    //   81: invokevirtual 656	com/kenai/jbosh/HTTPExchange:getRequest	()Lcom/kenai/jbosh/AbstractBody;
    //   84: getstatic 331	com/kenai/jbosh/Attributes:RID	Lcom/kenai/jbosh/BodyQName;
    //   87: invokevirtual 557	com/kenai/jbosh/AbstractBody:getAttribute	(Lcom/kenai/jbosh/BodyQName;)Ljava/lang/String;
    //   90: invokevirtual 108	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 113	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 722	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   99: aload_0
    //   100: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   103: invokevirtual 445	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   106: aload_0
    //   107: getfield 214	com/kenai/jbosh/BOSHClient:exchanges	Ljava/util/Queue;
    //   110: aload_2
    //   111: invokeinterface 659 2 0
    //   116: pop
    //   117: aload_0
    //   118: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   121: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   124: goto -112 -> 12
    //   127: astore_1
    //   128: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   131: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   134: ldc_w 726
    //   137: invokevirtual 722	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;)V
    //   140: aload_1
    //   141: athrow
    //   142: astore 5
    //   144: aload_0
    //   145: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   148: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   151: aload 5
    //   153: athrow
    //   154: aload_0
    //   155: aload 4
    //   157: invokespecial 742	com/kenai/jbosh/BOSHClient:processExchange	(Lcom/kenai/jbosh/HTTPExchange;)V
    //   160: goto -148 -> 12
    //   163: aload_2
    //   164: astore 4
    //   166: goto -12 -> 154
    //
    // Exception table:
    //   from	to	target	type
    //   12	17	127	finally
    //   34	45	127	finally
    //   49	56	127	finally
    //   61	106	127	finally
    //   117	124	127	finally
    //   144	154	127	finally
    //   154	160	127	finally
    //   106	117	142	finally
  }

  private long processPauseRequest(AbstractBody paramAbstractBody)
  {
    assertLocked();
    if ((this.cmParams != null) && (this.cmParams.getMaxPause() != null))
      try
      {
        AttrPause localAttrPause = AttrPause.createFromString(paramAbstractBody.getAttribute(Attributes.PAUSE));
        if (localAttrPause != null)
        {
          long l = localAttrPause.getInMilliseconds() - PAUSE_MARGIN;
          if (l < 0L)
          {
            int i = EMPTY_REQUEST_DELAY;
            l = i;
          }
          return l;
        }
      }
      catch (BOSHException localBOSHException)
      {
        LOG.log(Level.FINEST, "Could not extract", localBOSHException);
      }
    return getDefaultEmptyRequestDelay();
  }

  private void processRequestAcknowledgements(AbstractBody paramAbstractBody1, AbstractBody paramAbstractBody2)
  {
    assertLocked();
    if (!this.cmParams.isAckingRequests());
    while (paramAbstractBody2.getAttribute(Attributes.REPORT) != null)
      return;
    String str = paramAbstractBody2.getAttribute(Attributes.ACK);
    if (str == null);
    for (Long localLong = Long.valueOf(Long.parseLong(paramAbstractBody1.getAttribute(Attributes.RID))); ; localLong = Long.valueOf(Long.parseLong(str)))
    {
      if (LOG.isLoggable(Level.FINEST))
        LOG.finest("Removing pending acks up to: " + localLong);
      Iterator localIterator = this.pendingRequestAcks.iterator();
      while (localIterator.hasNext())
        if (Long.valueOf(Long.parseLong(((AbstractBody)localIterator.next()).getAttribute(Attributes.RID))).compareTo(localLong) <= 0)
          localIterator.remove();
      break;
    }
  }

  private void processResponseAcknowledgementData(AbstractBody paramAbstractBody)
  {
    assertLocked();
    Long localLong1 = Long.valueOf(Long.parseLong(paramAbstractBody.getAttribute(Attributes.RID)));
    if (this.responseAck.equals(Long.valueOf(-1L)))
      this.responseAck = localLong1;
    while (true)
    {
      return;
      this.pendingResponseAcks.add(localLong1);
      for (Long localLong2 = Long.valueOf(1L + this.responseAck.longValue()); (!this.pendingResponseAcks.isEmpty()) && (localLong2.equals(this.pendingResponseAcks.first())); localLong2 = Long.valueOf(1L + localLong2.longValue()))
      {
        this.responseAck = localLong2;
        this.pendingResponseAcks.remove(localLong2);
      }
    }
  }

  private HTTPExchange processResponseAcknowledgementReport(AbstractBody paramAbstractBody)
    throws BOSHException
  {
    Object localObject1 = null;
    assertLocked();
    String str = paramAbstractBody.getAttribute(Attributes.REPORT);
    if (str == null)
      return null;
    Long localLong1 = Long.valueOf(Long.parseLong(str));
    Long localLong2 = Long.valueOf(Long.parseLong(paramAbstractBody.getAttribute(Attributes.TIME)));
    if (LOG.isLoggable(Level.FINE))
      LOG.fine("Received report of missing request (RID=" + localLong1 + ", time=" + localLong2 + "ms)");
    Iterator localIterator = this.pendingRequestAcks.iterator();
    Object localObject2;
    if ((localIterator.hasNext()) && (localObject1 == null))
    {
      localObject2 = (AbstractBody)localIterator.next();
      if (!localLong1.equals(Long.valueOf(Long.parseLong(((AbstractBody)localObject2).getAttribute(Attributes.RID)))))
        break label237;
    }
    while (true)
    {
      localObject1 = localObject2;
      break;
      if (localObject1 == null)
        throw new BOSHException("Report of missing message with RID '" + str + "' but local copy of that request was not found");
      HTTPExchange localHTTPExchange = new HTTPExchange(localObject1);
      this.exchanges.add(localHTTPExchange);
      this.notEmpty.signalAll();
      return localHTTPExchange;
      label237: localObject2 = localObject1;
    }
  }

  private void scheduleEmptyRequest(long paramLong)
  {
    assertLocked();
    if (paramLong < 0L)
      throw new IllegalArgumentException("Empty request delay must be >= 0 (was: " + paramLong + ")");
    clearEmptyRequest();
    if (!isWorking())
      return;
    if (LOG.isLoggable(Level.FINER))
      LOG.finer("Scheduling empty request in " + paramLong + "ms");
    try
    {
      this.emptyRequestFuture = this.schedExec.schedule(this.emptyRequestRunnable, paramLong, TimeUnit.MILLISECONDS);
      this.drained.signalAll();
      return;
    }
    catch (RejectedExecutionException localRejectedExecutionException)
    {
      while (true)
        LOG.log(Level.FINEST, "Could not schedule empty request", localRejectedExecutionException);
    }
  }

  private void sendEmptyRequest()
  {
    assertUnlocked();
    LOG.finest("Sending empty request");
    try
    {
      send(ComposableBody.builder().build());
      return;
    }
    catch (BOSHException localBOSHException)
    {
      dispose(localBOSHException);
    }
  }

  public void addBOSHClientConnListener(BOSHClientConnListener paramBOSHClientConnListener)
  {
    if (paramBOSHClientConnListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.connListeners.add(paramBOSHClientConnListener);
  }

  public void addBOSHClientRequestListener(BOSHClientRequestListener paramBOSHClientRequestListener)
  {
    if (paramBOSHClientRequestListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.requestListeners.add(paramBOSHClientRequestListener);
  }

  public void addBOSHClientResponseListener(BOSHClientResponseListener paramBOSHClientResponseListener)
  {
    if (paramBOSHClientResponseListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.responseListeners.add(paramBOSHClientResponseListener);
  }

  public void close()
  {
    dispose(new BOSHException("Session explicitly closed by caller"));
  }

  public void disconnect()
    throws BOSHException
  {
    disconnect(ComposableBody.builder().build());
  }

  public void disconnect(ComposableBody paramComposableBody)
    throws BOSHException
  {
    if (paramComposableBody == null)
      throw new IllegalArgumentException("Message body may not be null");
    ComposableBody.Builder localBuilder = paramComposableBody.rebuild();
    localBuilder.setAttribute(Attributes.TYPE, "terminate");
    send(localBuilder.build());
  }

  void drain()
  {
    this.lock.lock();
    try
    {
      LOG.finest("Waiting while draining...");
      while (isWorking())
      {
        if (this.emptyRequestFuture != null)
        {
          boolean bool = this.emptyRequestFuture.isDone();
          if (!bool)
            break;
        }
        try
        {
          this.drained.await();
        }
        catch (InterruptedException localInterruptedException)
        {
          LOG.log(Level.FINEST, "Interrupted", localInterruptedException);
        }
      }
    }
    finally
    {
      this.lock.unlock();
    }
    LOG.finest("Drained");
    this.lock.unlock();
  }

  public BOSHClientConfig getBOSHClientConfig()
  {
    return this.cfg;
  }

  CMSessionParams getCMSessionParams()
  {
    this.lock.lock();
    try
    {
      CMSessionParams localCMSessionParams = this.cmParams;
      return localCMSessionParams;
    }
    finally
    {
      this.lock.unlock();
    }
  }

  // ERROR //
  public boolean pause()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial 443	com/kenai/jbosh/BOSHClient:assertUnlocked	()V
    //   4: aload_0
    //   5: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   8: invokevirtual 445	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   11: aload_0
    //   12: getfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnonnull +12 -> 29
    //   20: aload_0
    //   21: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   24: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   27: iconst_0
    //   28: ireturn
    //   29: aload_0
    //   30: getfield 348	com/kenai/jbosh/BOSHClient:cmParams	Lcom/kenai/jbosh/CMSessionParams;
    //   33: invokevirtual 746	com/kenai/jbosh/CMSessionParams:getMaxPause	()Lcom/kenai/jbosh/AttrMaxPause;
    //   36: astore_3
    //   37: aload_3
    //   38: ifnonnull +12 -> 50
    //   41: aload_0
    //   42: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   45: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   48: iconst_0
    //   49: ireturn
    //   50: aload_0
    //   51: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   54: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   57: aload_0
    //   58: invokestatic 846	com/kenai/jbosh/ComposableBody:builder	()Lcom/kenai/jbosh/ComposableBody$Builder;
    //   61: getstatic 617	com/kenai/jbosh/Attributes:PAUSE	Lcom/kenai/jbosh/BodyQName;
    //   64: aload_3
    //   65: invokevirtual 880	com/kenai/jbosh/AttrMaxPause:toString	()Ljava/lang/String;
    //   68: invokevirtual 269	com/kenai/jbosh/ComposableBody$Builder:setAttribute	(Lcom/kenai/jbosh/BodyQName;Ljava/lang/String;)Lcom/kenai/jbosh/ComposableBody$Builder;
    //   71: invokevirtual 345	com/kenai/jbosh/ComposableBody$Builder:build	()Lcom/kenai/jbosh/ComposableBody;
    //   74: invokevirtual 849	com/kenai/jbosh/BOSHClient:send	(Lcom/kenai/jbosh/ComposableBody;)V
    //   77: iconst_1
    //   78: ireturn
    //   79: astore_1
    //   80: aload_0
    //   81: getfield 164	com/kenai/jbosh/BOSHClient:lock	Ljava/util/concurrent/locks/ReentrantLock;
    //   84: invokevirtual 450	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   87: aload_1
    //   88: athrow
    //   89: astore 4
    //   91: getstatic 99	com/kenai/jbosh/BOSHClient:LOG	Ljava/util/logging/Logger;
    //   94: getstatic 394	java/util/logging/Level:FINEST	Ljava/util/logging/Level;
    //   97: ldc_w 882
    //   100: aload 4
    //   102: invokevirtual 398	java/util/logging/Logger:log	(Ljava/util/logging/Level;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   105: goto -28 -> 77
    //
    // Exception table:
    //   from	to	target	type
    //   11	16	79	finally
    //   29	37	79	finally
    //   57	77	89	com/kenai/jbosh/BOSHException
  }

  public void removeBOSHClientConnListener(BOSHClientConnListener paramBOSHClientConnListener)
  {
    if (paramBOSHClientConnListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.connListeners.remove(paramBOSHClientConnListener);
  }

  public void removeBOSHClientRequestListener(BOSHClientRequestListener paramBOSHClientRequestListener)
  {
    if (paramBOSHClientRequestListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.requestListeners.remove(paramBOSHClientRequestListener);
  }

  public void removeBOSHClientResponseListener(BOSHClientResponseListener paramBOSHClientResponseListener)
  {
    if (paramBOSHClientResponseListener == null)
      throw new IllegalArgumentException("Listener may not be null");
    this.responseListeners.remove(paramBOSHClientResponseListener);
  }

  public void send(ComposableBody paramComposableBody)
    throws BOSHException
  {
    assertUnlocked();
    if (paramComposableBody == null)
      throw new IllegalArgumentException("Message body may not be null");
    this.lock.lock();
    try
    {
      blockUntilSendable(paramComposableBody);
      if ((!isWorking()) && (!isTermination(paramComposableBody)))
        throw new BOSHException("Cannot send message when session is closed");
    }
    finally
    {
      this.lock.unlock();
    }
    long l = this.requestIDSeq.getNextRID();
    CMSessionParams localCMSessionParams = this.cmParams;
    ComposableBody localComposableBody;
    if ((localCMSessionParams == null) && (this.exchanges.isEmpty()))
      localComposableBody = applySessionCreationRequest(l, paramComposableBody);
    while (true)
    {
      HTTPExchange localHTTPExchange = new HTTPExchange(localComposableBody);
      this.exchanges.add(localHTTPExchange);
      this.notEmpty.signalAll();
      clearEmptyRequest();
      this.lock.unlock();
      AbstractBody localAbstractBody = localHTTPExchange.getRequest();
      localHTTPExchange.setHTTPResponse(this.httpSender.send(localCMSessionParams, localAbstractBody));
      fireRequestSent(localAbstractBody);
      return;
      localComposableBody = applySessionData(l, paramComposableBody);
      if (this.cmParams.isAckingRequests())
        this.pendingRequestAcks.add(localComposableBody);
    }
  }

  void setExchangeInterceptor(ExchangeInterceptor paramExchangeInterceptor)
  {
    this.exchInterceptor.set(paramExchangeInterceptor);
  }

  static abstract class ExchangeInterceptor
  {
    abstract HTTPExchange interceptExchange(HTTPExchange paramHTTPExchange);
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.BOSHClient
 * JD-Core Version:    0.6.2
 */