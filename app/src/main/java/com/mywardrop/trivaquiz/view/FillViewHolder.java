package com.mywardrop.trivaquiz.view;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mywardrop.trivaquiz.R;

public class FillViewHolder extends RecyclerView.ViewHolder{
    private TextView txtQuestion;
    private ImageView imgBtnTrue;
    private ImageView imgBtnFalse;
    public FillViewHolder(@NonNull View itemView)
    {
        super(itemView);

        txtQuestion = itemView.findViewById(R.id.fill_question_text);
        imgBtnTrue = itemView.findViewById(R.id.trueBtn);
        imgBtnFalse = itemView.findViewById(R.id.falseBtn);
    }

    public TextView getTxtQuestion() {
        return txtQuestion;
    }

    public ImageView getImgBtnFalse() {
        return imgBtnFalse;
    }
    public ImageView getImgBtnTrue() {
        return imgBtnTrue;
    }

}
