package com.lermao.lmbshop.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



/**
 * Fragment基类，所有的Fragment都需要继承此类。
 * 封装查看子控件，设置监听器，初始化数据
 *
 * @author WJQ
 */
public abstract class BaseFragment extends Fragment
		implements IUIOperation {

	/** 管理Fragment的Activity */
	public BaseActivity mActivity;

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化Activity对象
		mActivity = (BaseActivity)getActivity();
	}

	/** Fragment显示的布局 */
	public View mRoot;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		if (mRoot == null) {
			mRoot = inflater.inflate(getLayoutRes(), null);

			initView();
			initListener();
			initData();
			
		} else {
			// 解除mRoot与其父控件的关系
			unbindWidthParent(mRoot);
		}
		
		return mRoot;
	}

	/**
	 * 解除父子控件关系
	 * 
	 * @param view
	 */
	public void unbindWidthParent(View view) {
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
		}
	}


}

