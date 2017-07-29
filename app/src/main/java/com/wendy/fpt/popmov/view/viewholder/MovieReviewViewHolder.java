package com.wendy.fpt.popmov.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wendy.fpt.popmov.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieReviewViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.review_author)
    TextView reviewAuthor;
    @BindView(R.id.review_content)
    TextView reviewContent;

    public MovieReviewViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindReview(String author, String content) {
        reviewAuthor.setText(author);
        reviewContent.setText(content);
    }
}
