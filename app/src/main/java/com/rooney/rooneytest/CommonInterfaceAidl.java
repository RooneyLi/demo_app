package com.rooney.rooneytest;

import android.os.RemoteException;

public class CommonInterfaceAidl extends CommonInterface.Stub {
    @Override
    public String getString() throws RemoteException {
        return "Rooney test";
    }
}
