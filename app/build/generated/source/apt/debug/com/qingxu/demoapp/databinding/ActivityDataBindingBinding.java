package com.qingxu.demoapp.databinding;
import com.qingxu.demoapp.R;
import com.qingxu.demoapp.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
@javax.annotation.Generated("Android Data Binding")
public class ActivityDataBindingBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.recyclerView, 9);
    }
    // views
    @NonNull
    public final android.widget.Button button1;
    @NonNull
    public final android.widget.Button button2;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView1;
    @NonNull
    private final android.widget.TextView mboundView2;
    @NonNull
    private final android.widget.TextView mboundView3;
    @NonNull
    private final android.widget.TextView mboundView4;
    @NonNull
    private final android.widget.TextView mboundView5;
    @NonNull
    private final android.widget.ImageView mboundView8;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerView;
    // variables
    @Nullable
    private java.lang.Boolean mErr;
    @Nullable
    private android.graphics.drawable.Drawable mSrc;
    @Nullable
    private com.qingxu.demoapp.databinding.UserInfo mUser;
    @Nullable
    private android.view.View.OnClickListener mOnClick;
    @Nullable
    private int mCount;
    @Nullable
    private java.lang.String mStr;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDataBindingBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.button1 = (android.widget.Button) bindings[6];
        this.button1.setTag(null);
        this.button2 = (android.widget.Button) bindings[7];
        this.button2.setTag(null);
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.TextView) bindings[1];
        this.mboundView1.setTag(null);
        this.mboundView2 = (android.widget.TextView) bindings[2];
        this.mboundView2.setTag(null);
        this.mboundView3 = (android.widget.TextView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.mboundView5 = (android.widget.TextView) bindings[5];
        this.mboundView5.setTag(null);
        this.mboundView8 = (android.widget.ImageView) bindings[8];
        this.mboundView8.setTag(null);
        this.recyclerView = (android.support.v7.widget.RecyclerView) bindings[9];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x40L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.err == variableId) {
            setErr((java.lang.Boolean) variable);
        }
        else if (BR.src == variableId) {
            setSrc((android.graphics.drawable.Drawable) variable);
        }
        else if (BR.user == variableId) {
            setUser((com.qingxu.demoapp.databinding.UserInfo) variable);
        }
        else if (BR.onClick == variableId) {
            setOnClick((android.view.View.OnClickListener) variable);
        }
        else if (BR.count == variableId) {
            setCount((int) variable);
        }
        else if (BR.str == variableId) {
            setStr((java.lang.String) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setErr(@Nullable java.lang.Boolean Err) {
        this.mErr = Err;
        synchronized(this) {
            mDirtyFlags |= 0x2L;
        }
        notifyPropertyChanged(BR.err);
        super.requestRebind();
    }
    @Nullable
    public java.lang.Boolean getErr() {
        return mErr;
    }
    public void setSrc(@Nullable android.graphics.drawable.Drawable Src) {
        this.mSrc = Src;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.src);
        super.requestRebind();
    }
    @Nullable
    public android.graphics.drawable.Drawable getSrc() {
        return mSrc;
    }
    public void setUser(@Nullable com.qingxu.demoapp.databinding.UserInfo User) {
        updateRegistration(0, User);
        this.mUser = User;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.user);
        super.requestRebind();
    }
    @Nullable
    public com.qingxu.demoapp.databinding.UserInfo getUser() {
        return mUser;
    }
    public void setOnClick(@Nullable android.view.View.OnClickListener OnClick) {
        this.mOnClick = OnClick;
        synchronized(this) {
            mDirtyFlags |= 0x8L;
        }
        notifyPropertyChanged(BR.onClick);
        super.requestRebind();
    }
    @Nullable
    public android.view.View.OnClickListener getOnClick() {
        return mOnClick;
    }
    public void setCount(int Count) {
        this.mCount = Count;
        synchronized(this) {
            mDirtyFlags |= 0x10L;
        }
        notifyPropertyChanged(BR.count);
        super.requestRebind();
    }
    public int getCount() {
        return mCount;
    }
    public void setStr(@Nullable java.lang.String Str) {
        this.mStr = Str;
        synchronized(this) {
            mDirtyFlags |= 0x20L;
        }
        notifyPropertyChanged(BR.str);
        super.requestRebind();
    }
    @Nullable
    public java.lang.String getStr() {
        return mStr;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeUser((com.qingxu.demoapp.databinding.UserInfo) object, fieldId);
        }
        return false;
    }
    private boolean onChangeUser(com.qingxu.demoapp.databinding.UserInfo User, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean androidDatabindingDynamicUtilSafeUnboxErr = false;
        java.lang.String userName = null;
        java.lang.Boolean err = mErr;
        java.lang.String stringValueOfCount = null;
        android.graphics.drawable.Drawable src = mSrc;
        java.lang.String userAge = null;
        com.qingxu.demoapp.databinding.UserInfo user = mUser;
        android.view.View.OnClickListener onClick = mOnClick;
        java.lang.String errJavaLangStringErrTrueJavaLangStringErrFalse = null;
        int count = mCount;
        java.lang.String str = mStr;

        if ((dirtyFlags & 0x42L) != 0) {



                // read android.databinding.DynamicUtil.safeUnbox(err)
                androidDatabindingDynamicUtilSafeUnboxErr = android.databinding.DynamicUtil.safeUnbox(err);
            if((dirtyFlags & 0x42L) != 0) {
                if(androidDatabindingDynamicUtilSafeUnboxErr) {
                        dirtyFlags |= 0x100L;
                }
                else {
                        dirtyFlags |= 0x80L;
                }
            }


                // read android.databinding.DynamicUtil.safeUnbox(err) ? "err为true" : "err为false"
                errJavaLangStringErrTrueJavaLangStringErrFalse = ((androidDatabindingDynamicUtilSafeUnboxErr) ? ("err为true") : ("err为false"));
        }
        if ((dirtyFlags & 0x44L) != 0) {
        }
        if ((dirtyFlags & 0x41L) != 0) {



                if (user != null) {
                    // read user.name
                    userName = user.getName();
                    // read user.age
                    userAge = user.getAge();
                }
        }
        if ((dirtyFlags & 0x48L) != 0) {
        }
        if ((dirtyFlags & 0x50L) != 0) {



                // read String.valueOf(count)
                stringValueOfCount = java.lang.String.valueOf(count);
        }
        if ((dirtyFlags & 0x60L) != 0) {
        }
        // batch finished
        if ((dirtyFlags & 0x48L) != 0) {
            // api target 1

            this.button1.setOnClickListener(onClick);
            this.button2.setOnClickListener(onClick);
        }
        if ((dirtyFlags & 0x41L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, userName);
            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, userAge);
        }
        if ((dirtyFlags & 0x60L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView3, str);
        }
        if ((dirtyFlags & 0x50L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, stringValueOfCount);
        }
        if ((dirtyFlags & 0x42L) != 0) {
            // api target 1

            android.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView5, errJavaLangStringErrTrueJavaLangStringErrFalse);
        }
        if ((dirtyFlags & 0x44L) != 0) {
            // api target 1

            android.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(this.mboundView8, src);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityDataBindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDataBindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityDataBindingBinding>inflate(inflater, com.qingxu.demoapp.R.layout.activity_data_binding, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityDataBindingBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDataBindingBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.qingxu.demoapp.R.layout.activity_data_binding, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityDataBindingBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityDataBindingBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_data_binding_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityDataBindingBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): user
        flag 1 (0x2L): err
        flag 2 (0x3L): src
        flag 3 (0x4L): onClick
        flag 4 (0x5L): count
        flag 5 (0x6L): str
        flag 6 (0x7L): null
        flag 7 (0x8L): android.databinding.DynamicUtil.safeUnbox(err) ? "err为true" : "err为false"
        flag 8 (0x9L): android.databinding.DynamicUtil.safeUnbox(err) ? "err为true" : "err为false"
    flag mapping end*/
    //end
}