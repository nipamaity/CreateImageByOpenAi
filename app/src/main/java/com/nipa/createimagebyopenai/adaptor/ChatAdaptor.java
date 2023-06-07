package com.nipa.createimagebyopenai.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nipa.createimagebyopenai.R;
import com.nipa.createimagebyopenai.databinding.AdaptorChatBinding;
import com.nipa.createimagebyopenai.model.ChatModel;

import java.util.List;

public class ChatAdaptor extends RecyclerView.Adapter<ChatAdaptor.ViewHolder> {
    Context context;

    public ChatAdaptor(Context context, List<ChatModel> chatList) {
        this.context = context;
        this.chatList = chatList;
    }

    List<ChatModel> chatList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(AdaptorChatBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(chatList.get(position).isChatByMe()){
            holder.chatBinding.tvMessageMe.setVisibility(View.VISIBLE);
            holder.chatBinding.tvMessageBot.setVisibility(View.GONE);
            holder.chatBinding.tvMessageMe.setText(chatList.get(position).getMessage());
        }else {
            holder.chatBinding.tvMessageMe.setVisibility(View.GONE);
            holder.chatBinding.tvMessageBot.setVisibility(View.VISIBLE);
           // holder.chatBinding.tvMessageBot.setText(chatList.get(position).getMessage());
                Glide.with(context)
                    .load(chatList.get(position).getMessage())
                        .placeholder(R.drawable.animated_placeholder)
                    .into(holder.chatBinding.tvMessageBot);
        }

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private AdaptorChatBinding chatBinding;
        public ViewHolder(AdaptorChatBinding chatBinding) {
            super(chatBinding.getRoot());
            this.chatBinding = chatBinding;
        }
    }

}
