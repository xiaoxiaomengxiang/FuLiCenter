package com.example.winston.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.winston.myapplication.R;
import com.example.winston.myapplication.bean.CategoryChildBean;
import com.example.winston.myapplication.bean.CategoryGroupBean;
import com.example.winston.myapplication.utils.ImageLoader;
import com.example.winston.myapplication.utils.L;
import com.example.winston.myapplication.utils.MFGT;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Winston on 2016/10/20.
 */

public class CategoryAdapter extends BaseExpandableListAdapter {
    Context mContext;
    ArrayList<CategoryGroupBean> mGroupList;
    ArrayList<ArrayList<CategoryChildBean>> mChildList;

    public CategoryAdapter(Context Context, ArrayList<CategoryGroupBean> GroupList,
                           ArrayList<ArrayList<CategoryChildBean>> ChildList) {
        mContext = Context;
        mGroupList = new ArrayList<>();
        mGroupList.addAll(GroupList);
        mChildList = new ArrayList<>();
        mChildList.addAll(ChildList);
    }

    @Override
    public int getGroupCount() {
        return mGroupList != null ? mGroupList.size() : 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChildList != null && mChildList.get(groupPosition) != null ?
                mChildList.get(groupPosition).size() : 0;
    }

    @Override
    public CategoryGroupBean getGroup(int groupPosition) {
        return mGroupList != null ? mGroupList.get(groupPosition) : null;
    }

    @Override
    public CategoryChildBean getChild(int groupPosition, int childPosition) {
        return mChildList != null && mChildList.get(groupPosition) != null ?
                mChildList.get(groupPosition).get(childPosition) : null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpand, View view, ViewGroup ViewGroup) {
        GroupViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_categroy_group, null);
            holder = new GroupViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (GroupViewHolder) view.getTag();
        }
        CategoryGroupBean group = getGroup(groupPosition);
        if (group != null) {
            ImageLoader.downloadImg(mContext, holder.mivGroupThumb, group.getImageUrl());
            holder.mtvGroupName.setText(group.getName());
            holder.mivIndicator.setImageResource(isExpand ? R.mipmap.expand_off : R.mipmap.expand_on);
        }
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        ChildViewHolder holder;
        if (view == null) {
            view = View.inflate(mContext, R.layout.item_category_child, null);
            holder = new ChildViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ChildViewHolder) view.getTag();
        }
        final CategoryChildBean child = getChild(groupPosition, childPosition);
        if (child != null) {
            ImageLoader.downloadImg(mContext, holder.mivCategoryChildThumb, child.getImageUrl());
            holder.mtvCategoryChildName.setText(child.getName());
            holder.mlayoutCategoryChild.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ArrayList<CategoryChildBean> list = mChildList.get(groupPosition);
                    String groupName = mGroupList.get(groupPosition).getName();
                    MFGT.gotoCategoryChildActivity(mContext,child.getId(),groupName,list);
                }
            });
        }
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public void initData(ArrayList<CategoryGroupBean> groupList, ArrayList<ArrayList<CategoryChildBean>> childList) {
        if (mGroupList != null) {
            mGroupList.clear();
        }
            mGroupList.addAll(groupList);
        if (mChildList != null) {
            mChildList.clear();
        }
            mChildList.addAll(childList);
        L.e("ssssssssssss"+mChildList.size()+"   "+ mGroupList.size());
            notifyDataSetChanged();
    }

    class GroupViewHolder {
        @BindView(R.id.iv_group_thumb)
        ImageView mivGroupThumb;
        @BindView(R.id.tv_group_name)
        TextView mtvGroupName;
        @BindView(R.id.iv_indicator)
        ImageView mivIndicator;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    class ChildViewHolder {
        @BindView(R.id.iv_category_child_thumb)
        ImageView mivCategoryChildThumb;
        @BindView(R.id.tv_category_child_name)
        TextView mtvCategoryChildName;
        @BindView(R.id.layout_category_child)
        RelativeLayout mlayoutCategoryChild;

        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
