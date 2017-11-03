package is.loskutov.alliance.system;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import is.loskutov.alliance.R;
import is.loskutov.alliance.model.Questions;

import static android.R.color.tertiary_text_dark;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;
    private Questions[] data;

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView question, answer1, answer2, answer3;

        ViewHolder(View itemView) {
            super(itemView);

            question = (TextView) itemView.findViewById(R.id.question);
            answer1 = (TextView) itemView.findViewById(R.id.answer1);
            answer2 = (TextView) itemView.findViewById(R.id.answer2);
            answer3 = (TextView) itemView.findViewById(R.id.answer3);
        }
    }

    public ResultAdapter(Questions[] data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public ResultAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_list_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ResultAdapter.ViewHolder holder, int position) {
        final Questions question = data[position];
        int rightAnswer = question.getRightAnswer();

        String userAnswer = question.getUserAnswer();

        holder.question.setText(question.getQuestion());
        holder.answer1.setText(question.getAnswer1());
        holder.answer2.setText(question.getAnswer2());
        holder.answer3.setText(question.getAnswer3());

        highlightAnswer(holder.answer1, 1, rightAnswer, userAnswer);
        highlightAnswer(holder.answer2, 2, rightAnswer, userAnswer);
        highlightAnswer(holder.answer3, 3, rightAnswer, userAnswer);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    private void highlightAnswer(TextView answer, int number, int rightAnswer, String userAnswer) {
        if (rightAnswer == number) {
            answer.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));
        } else if (answer.getText().toString().contentEquals(userAnswer)) {
            answer.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
        } else {
            answer.setTextColor(ContextCompat.getColor(context, tertiary_text_dark));
        }
    }
}
