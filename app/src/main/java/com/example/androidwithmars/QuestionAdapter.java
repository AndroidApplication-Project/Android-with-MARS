package com.example.androidwithmars;

import android.content.pm.VersionedPackage;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionVH>{
    List<Question>  questionList;


    public QuestionAdapter(List<Question> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_parent,parent,false);
        return new QuestionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionVH holder, int position) {
        Question question=questionList.get(position);
        holder.questionTxt.setText(question.getCodeQuestion());
        holder.answerTxt.setText(question.getCodeAnswer());

        boolean isExpandable=questionList.get(position).isExpandable();
        holder.expandableLayout.setVisibility(isExpandable ?View.VISIBLE:View.GONE);

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class QuestionVH extends RecyclerView.ViewHolder {
        TextView questionTxt,answerTxt;
        LinearLayout linearLayout;
        RelativeLayout expandableLayout;

        public QuestionVH(@NonNull View itemView) {
            super(itemView);
            questionTxt=itemView.findViewById(R.id.parentTitle);
            answerTxt=itemView.findViewById(R.id.api_answer);

            expandableLayout=itemView.findViewById(R.id.expandableLayout);
            linearLayout=itemView.findViewById(R.id.linear_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Question question=questionList.get(getAdapterPosition());
                    question.setExpandable(!question.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}

