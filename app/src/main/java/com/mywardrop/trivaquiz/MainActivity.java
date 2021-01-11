package com.mywardrop.trivaquiz;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mywardrop.trivaquiz.controller.CardAdapter;
import com.mywardrop.trivaquiz.controller.QuestionsInterface;
import com.mywardrop.trivaquiz.model.QuizManager;
import com.mywardrop.trivaquiz.model.QuizQuestion;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements QuestionsInterface, CardStackListener {

    final static String TAG = "app";

    private String url;
    private TextView tv;
    private CardStackView mCardStackView;
    private List<QuizQuestion> questionList;
    private QuizManager quizManager;
    private CardStackLayoutManager mCardStackLayoutManager;
    private CardAdapter cardAdapter;
    //CardStackView card;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCardStackLayoutManager = new CardStackLayoutManager(this,this);
        mCardStackView= findViewById(R.id.trueFalseCardView);
        questionList = new ArrayList<>();
        cardAdapter =new CardAdapter(this,questionList);
        quizManager = new QuizManager(this);
        quizManager.getQuizQuestions(cardAdapter);
        mCardStackView.setLayoutManager(mCardStackLayoutManager);
        mCardStackView.setAdapter(cardAdapter);
        mCardStackLayoutManager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);

    }

    @Override
    public void getQuestion(QuizQuestion question) {
        questionList.add(question);
    }

    @Override
    public void onCardSwiped(Direction direction) {

        int index = mCardStackLayoutManager.getTopPosition()-1;
        if(direction.toString()=="Right") {
            if (questionList.get(index).getTrueAnswer())
                Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Wrong",Toast.LENGTH_SHORT).show();
        }
        else {
            if (questionList.get(index).getTrueAnswer())
                Toast.makeText(this,"Wrong..",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Correct..",Toast.LENGTH_SHORT).show();
        }


    }

    ////Below code not used
    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {

    }

    @Override
    public void onCardDisappeared(View view, int position) {

    }
}