package com.qingxu.demoapp.ui.databinding;

import android.databinding.ViewDataBinding;
import android.view.View;

/**
 * Created by jxy on 2018/1/3.
 */

public class CustomBinding extends ViewDataBinding{

    /**
     * @param bindingComponent
     * @param root
     * @param localFieldCount
     * @hide
     */
    protected CustomBinding(android.databinding.DataBindingComponent bindingComponent, View root, int localFieldCount) {
        super(bindingComponent, root, localFieldCount);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        return false;
    }

    @Override
    public boolean setVariable(int variableId, Object value) {
        return false;
    }

    @Override
    protected void executeBindings() {

    }

    @Override
    public void invalidateAll() {

    }

    @Override
    public boolean hasPendingBindings() {
        return false;
    }
}
