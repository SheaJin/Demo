
package android.databinding;
import com.qingxu.demoapp.BR;
@javax.annotation.Generated("Android Data Binding")
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 21;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.qingxu.demoapp.R.layout.activity_data_binding:
                    return com.qingxu.demoapp.databinding.ActivityDataBindingBinding.bind(view, bindingComponent);
                case com.qingxu.demoapp.R.layout.fragment_data_binding:
                    return com.qingxu.demoapp.databinding.FragmentDataBindingBinding.bind(view, bindingComponent);
                case com.qingxu.demoapp.R.layout.item_data_fragment:
                    return com.qingxu.demoapp.databinding.ItemDataFragmentBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1514339820: {
                if(tag.equals("layout/activity_data_binding_0")) {
                    return com.qingxu.demoapp.R.layout.activity_data_binding;
                }
                break;
            }
            case 1465484427: {
                if(tag.equals("layout/fragment_data_binding_0")) {
                    return com.qingxu.demoapp.R.layout.fragment_data_binding;
                }
                break;
            }
            case 1237144143: {
                if(tag.equals("layout/item_data_fragment_0")) {
                    return com.qingxu.demoapp.R.layout.item_data_fragment;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"age"
            ,"count"
            ,"err"
            ,"handler"
            ,"name"
            ,"onClick"
            ,"src"
            ,"str"
            ,"user"};
    }
}