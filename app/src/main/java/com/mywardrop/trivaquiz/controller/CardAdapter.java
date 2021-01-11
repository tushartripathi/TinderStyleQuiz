package com.mywardrop.trivaquiz.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mywardrop.trivaquiz.MainActivity;
import com.mywardrop.trivaquiz.R;
import com.mywardrop.trivaquiz.model.QuizQuestion;
import com.mywardrop.trivaquiz.view.FillViewHolder;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<FillViewHolder>  {

    final static String TAG = "app";
    private Context mContext;
    private List<QuizQuestion> questionList;
    private LayoutInflater mInflater;
    private int index=0;



    public CardAdapter(Context mContext, List<QuizQuestion> questionList)
    {
        Log.d(TAG,"11");
        this.mContext = mContext;
        Log.d(TAG,questionList.size()+" const size");
        this.questionList = questionList;
        mInflater = LayoutInflater.from(mContext);

    }
    @NonNull
    @Override
    public FillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //View is created from the file_view.xml file. Which will be returned to FillViewHolder
        View view = LayoutInflater.from(mContext).inflate(R.layout.file_view,parent,false);
        //This will call the viewHolder
        return new FillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FillViewHolder holder, final int position) {
        //Here we have access to FillViewHolder obj. Which contains all the member in fileView file
        //We can access them using holder obj and add values to it. from list using postition as index.
       // Log.d(TAG,index+" index : pos" + position);
        holder.getTxtQuestion().setText(questionList.get(position).getQuestionText());
        holder.getImgBtnTrue().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(questionList.get(position).getTrueAnswer())
                    Toast.makeText(mContext, "Correct", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Wrong", Toast.LENGTH_SHORT).show();

                Log.d(TAG,index+" slected answer = True" );
                //Log.d(TAG,questionList.get(index).getQuestionText()+"");
                //Log.d(TAG,questionList.get(index).getTrueAnswer()+"");
            }
        });

        holder.getImgBtnFalse().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!questionList.get(position).getTrueAnswer())
                        Toast.makeText(mContext, "Correct", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(mContext, "Wrong", Toast.LENGTH_SHORT).show();

                Log.d(TAG,index+" slected answer = False" );
                //Log.d(TAG,questionList.get(index).getQuestionText()+"");
                //Log.d(TAG,questionList.get(index).getTrueAnswer()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
      //  Log.d(TAG,questionList.size()+"size");
        return questionList.size();
    }
}
