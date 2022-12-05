package com.improve10x.templatesv3;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TemplateViewHolder extends RecyclerView.ViewHolder {

    public TextView messageTxt;

    public ImageView deleteBtn;

    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        messageTxt = itemView.findViewById(R.id.message_txt);
        deleteBtn = itemView.findViewById(R.id.delete_btn);
    }
}
