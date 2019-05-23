package com.example.chappiebot.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.chappiebot.MainActivity;

public class BaseFragment extends Fragment {
	public MainActivity mActivity;
	/**
	 * use for fix Can not perform this action after onSaveInstanceState at
	 * android
	 * .support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.
	 * java:1327)
	 */
	OnFragmentAttachedListener mListener = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mActivity = (MainActivity) this.getActivity();
	}

	public boolean onBackPressed() {
		return false;
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

	}

	/**
	 * use for fix Can not perform this action after onSaveInstanceState at
	 * android
	 * .support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.
	 * java:1327)
	 */
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try {
			mListener = (OnFragmentAttachedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentAttachedListener");
		}
	}

	/**
	 * use for fix Can not perform this action after onSaveInstanceState at
	 * android
	 * .support.v4.app.FragmentManagerImpl.checkStateLoss(FragmentManager.
	 * java:1327)
	 */
	public interface OnFragmentAttachedListener {
		public void OnFragmentAttached();
	}
}
