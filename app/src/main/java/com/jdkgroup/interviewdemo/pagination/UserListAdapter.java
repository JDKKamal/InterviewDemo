package com.jdkgroup.interviewdemo.pagination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jdkgroup.interviewdemo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<User> users;
    private Context context;

    public UserListAdapter(ArrayList<User> users, Context context) {
        this.context = context;
        this.users = users;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_user_list, parent, false);
        viewHolder = new UserViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        UserViewHolder userViewHolder = (UserViewHolder) holder;
        User user = users.get(position);
        userViewHolder.userName.setText(user.login);
        userViewHolder.loginId.setText(String.valueOf(user.id));
        if (!TextUtils.isEmpty(user.avatar_url)) {

        }
    }


    public void setUsers(List<User> u) {
        int count = getItemCount();
        users.addAll(u);
        notifyItemRangeInserted(count, u.size());
    }

    @Override
    public int getItemCount() {
        return users == null ? 0 : users.size();
    }

    public int getLastVisibleItemId() {
        if (users.isEmpty()) {
            return 0;
        }
        return users.get(users.size() - 1).id;
    }


    public static class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.userName)
        TextView userName;
        @BindView(R.id.loginId)
        TextView loginId;
        @BindView(R.id.userAvater)
        ImageView avatar;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
