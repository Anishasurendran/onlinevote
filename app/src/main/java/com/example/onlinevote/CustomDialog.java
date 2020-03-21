package com.example.onlinevote;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.onlinevote.models.Candidate;
import com.example.onlinevote.models.CandidateList;
import com.google.android.material.button.MaterialButton;

public class CustomDialog extends Dialog {

    TextView dialogTextView;
    MaterialButton cancelButton, confirmButton;
    CandidateList candidateList;
    CustomDialogInterface dialogInterface;

    public  CustomDialog(Context mContext, CustomDialogInterface dialogInterface){
        super(mContext);
        this.dialogInterface = dialogInterface;
    }


    public interface  CustomDialogInterface {
        public void onConfirmButtonClicked(CandidateList candidateList);
    }

    public void setCandidate(CandidateList candidateList) {
        this.candidateList = candidateList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_layout);
        dialogTextView =  findViewById(R.id.dialog_text);
        cancelButton = findViewById(R.id.dialog_cancel);
        confirmButton = findViewById(R.id.dialog_confirm);
        if(candidateList !=  null)
            dialogTextView.setText("Are you sure you want to vote for "+ candidateList.getCandidate().getName() + " ?");

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.onConfirmButtonClicked(candidateList);
            }
        });
    }
}
