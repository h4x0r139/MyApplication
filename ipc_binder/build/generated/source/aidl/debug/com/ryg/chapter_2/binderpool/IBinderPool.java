/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\yinxm\\Android_Workspace\\MyApplication\\ipc_binder\\src\\main\\aidl\\com\\ryg\\chapter_2\\binderpool\\IBinderPool.aidl
 */
package com.ryg.chapter_2.binderpool;
public interface IBinderPool extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ryg.chapter_2.binderpool.IBinderPool
{
private static final java.lang.String DESCRIPTOR = "com.ryg.chapter_2.binderpool.IBinderPool";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ryg.chapter_2.binderpool.IBinderPool interface,
 * generating a proxy if needed.
 */
public static com.ryg.chapter_2.binderpool.IBinderPool asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ryg.chapter_2.binderpool.IBinderPool))) {
return ((com.ryg.chapter_2.binderpool.IBinderPool)iin);
}
return new com.ryg.chapter_2.binderpool.IBinderPool.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_queryBinder:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
android.os.IBinder _result = this.queryBinder(_arg0);
reply.writeNoException();
reply.writeStrongBinder(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ryg.chapter_2.binderpool.IBinderPool
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * @param binderCode, the unique token of specific Binder<br/>
     * @return specific Binder who's token is binderCode.
     */
@Override public android.os.IBinder queryBinder(int binderCode) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
android.os.IBinder _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(binderCode);
mRemote.transact(Stub.TRANSACTION_queryBinder, _data, _reply, 0);
_reply.readException();
_result = _reply.readStrongBinder();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_queryBinder = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
/**
     * @param binderCode, the unique token of specific Binder<br/>
     * @return specific Binder who's token is binderCode.
     */
public android.os.IBinder queryBinder(int binderCode) throws android.os.RemoteException;
}
