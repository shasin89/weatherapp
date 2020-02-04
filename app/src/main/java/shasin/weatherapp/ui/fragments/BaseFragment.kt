package shasin.weatherapp.ui.fragments

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onGetInputData()
        onInject()
        onBindView(view)
        onBindData(view)
    }

    @LayoutRes
    protected abstract fun getLayoutResource(): Int

    protected open fun onBindView(view: View?) {}
    protected open fun onInject() {}
    protected open fun onBindData(view: View?) {}
    protected open fun onGetInputData() {}
}