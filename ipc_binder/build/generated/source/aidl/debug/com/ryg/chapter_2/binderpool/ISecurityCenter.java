/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: D:\\yinxm\\Android_Workspace\\MyApplication\\ipc_binder\\src\\main\\aidl\\com\\ryg\\chapter_2\\binderpool\\ISecurityCenter.aidl
 */
package com.ryg.chapter_2.binderpool;
public interface ISecurityCenter extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.ryg.chapter_2.binderpool.ISecurityCenter
{
private static final java.lang.String DESCRIPTOR = "com.ryg.chapter_2.binderpool.ISecurityCenter";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.ryg.chapter_2.binderpool.ISecurityCenter interface,
 * generating a proxy if needed.
 */
public static com.ryg.chapter_2.binderpool.ISecurityCenter asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.ryg.chapter_2.binderpool.ISecurityCenter))) {
return ((com.ryg.chapter_2.binderpool.ISecurityCenter)iin);
}
return new com.ryg.chapter_2.binderpool.ISecurityCenter.Stub.Proxy(obj);
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
case TRANSACTION_encrypt:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.encrypt(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_decrypt:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.decrypt(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.ryg.chapter_2.binderpool.ISecurityCenter
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
@Override public java.lang.String encrypt(java.lang.String content) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(content);
mRemote.transact(Stub.TRANSACTION_encrypt, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String decrypt(java.lang.String password) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(password);
mRemote.transact(Stub.TRANSACTION_decrypt, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_encrypt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_decrypt = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public java.lang.String encrypt(java.lang.String content) throws android.os.RemoteException;
public java.lang.String decrypt(java.lang.String password) throws android.os.RemoteException;
}
