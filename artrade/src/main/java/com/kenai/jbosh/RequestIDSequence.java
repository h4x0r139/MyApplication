package com.kenai.jbosh;

import java.security.SecureRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

final class RequestIDSequence
{
  private static final int INCREMENT_BITS = 32;
  private static final Lock LOCK = new ReentrantLock();
  private static final long MASK = 9007199254740991L;
  private static final int MAX_BITS = 53;
  private static final long MAX_INITIAL = 9007194959773696L;
  private static final long MIN_INCREMENTS = 4294967296L;
  private static final SecureRandom RAND = new SecureRandom();
  private AtomicLong nextRequestID = new AtomicLong();

  private long generateInitialValue()
  {
    LOCK.lock();
    try
    {
      long l2;
      do
      {
        long l1 = RAND.nextLong();
        l2 = l1 & 0xFFFFFFFF;
      }
      while (l2 > 9007194959773696L);
      return l2;
    }
    finally
    {
      LOCK.unlock();
    }
  }

  public long getNextRID()
  {
    return this.nextRequestID.getAndIncrement();
  }
}

/* Location:           D:\yinxm\Android\work\拍卖\apktool\artrade\classes_dex2jar.jar
 * Qualified Name:     com.kenai.jbosh.RequestIDSequence
 * JD-Core Version:    0.6.2
 */