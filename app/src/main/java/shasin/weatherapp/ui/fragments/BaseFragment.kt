package shasin.weatherapp.ui.fragments

import android.os.Bundle
import androidx.annotation.LayoutRes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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