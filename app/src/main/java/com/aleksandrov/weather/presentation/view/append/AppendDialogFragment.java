package com.aleksandrov.weather.presentation.view.append;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.aleksandrov.weather.R;

public class AppendDialogFragment extends DialogFragment {

    public static final String TAG = AppendDialogFragment.class.getSimpleName();

    interface AppendDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    private AppendDialogListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            mListener = (AppendDialogListener) getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(getTargetFragment().toString()
                    + " must implement NoticeDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setMessage(getContext().getResources().getString(R.string.message_location_add))
                .setPositiveButton(android.R.string.ok, (dialog, which) ->
                        mListener.onDialogPositiveClick(this))
                .setNegativeButton(android.R.string.cancel, (dialog, which) ->
                        mListener.onDialogNegativeClick(this))
                .create();
    }

}
