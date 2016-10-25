package com.example.winston.myapplication.net;

import android.content.Context;

import com.example.winston.myapplication.I;
import com.example.winston.myapplication.bean.BoutiqueBean;
import com.example.winston.myapplication.bean.CategoryChildBean;
import com.example.winston.myapplication.bean.CategoryGroupBean;
import com.example.winston.myapplication.bean.GoodsDetailsBean;
import com.example.winston.myapplication.bean.NewGoodsBean;
import com.example.winston.myapplication.bean.Result;
import com.example.winston.myapplication.utils.MD5;

import java.io.File;

/**
 * Created by Winston on 2016/10/17.
 */
public class NetDao {
    public static void downloadNewGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_NEW_BOUTIQUE_GOODS)
                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))
                .addParam(I.PAGE_ID,String.valueOf(pageId))
                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))
                .targetClass(NewGoodsBean[].class)
                .execute(listener);
    }

    public static void downloadGoodsDetail(Context context, int goodsId, OkHttpUtils.OnCompleteListener<GoodsDetailsBean> listener){
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_GOOD_DETAILS)
                .addParam(I.GoodsDetails.KEY_GOODS_ID,String.valueOf(goodsId))
                .targetClass(GoodsDetailsBean.class)
                .execute(listener);
    }

    public static void downloadBuotique(Context context, OkHttpUtils.OnCompleteListener<BoutiqueBean[]> listener){
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_BOUTIQUES)
                .targetClass(BoutiqueBean[].class)
                .execute(listener);
    }

    public static void downloadCategoryGroup(Context context, OkHttpUtils.OnCompleteListener<CategoryGroupBean[]> listener) {
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_GROUP)
                .targetClass(CategoryGroupBean[].class)
                .execute(listener);
    }
    public static void downloadCategoryChild(Context context, int parentId, OkHttpUtils.OnCompleteListener<CategoryChildBean[]> listener) {
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_CATEGORY_CHILDREN)
                .addParam(I.CategoryChild.PARENT_ID,String.valueOf(parentId))
                .targetClass(CategoryChildBean[].class)
                .execute(listener);
    }
    ////////////////////////////////////////////////////////////////////////////////////
    public static void downloadCategoryGoods(Context context,int catId, int pageId, OkHttpUtils.OnCompleteListener<NewGoodsBean[]> listener){
        OkHttpUtils utils = new OkHttpUtils(context);
        utils.setRequestUrl(I.REQUEST_FIND_GOODS_DETAILS)
                .addParam(I.NewAndBoutiqueGoods.CAT_ID,String.valueOf(catId))
                .addParam(I.PAGE_ID,String.valueOf(pageId))
                .addParam(I.PAGE_SIZE,String.valueOf(I.PAGE_SIZE_DEFAULT))
                .targetClass(NewGoodsBean[].class)
                .execute(listener);
    }
    public static void register(Context context, String username, String nickname, String password, OkHttpUtils.OnCompleteListener<Result> listener){
        OkHttpUtils<Result> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.NICK,nickname)
                .addParam(I.User.PASSWORD, MD5.getMessageDigest(password))
                .targetClass(Result.class)
                .post()
                .execute(listener);
    }
    public static void login(Context context, String username, String password, OkHttpUtils.OnCompleteListener<String> listener){
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME,username)
                .addParam(I.User.PASSWORD,MD5.getMessageDigest(password))
                .targetClass(String.class)
                .execute(listener);
    }
    public static void updateNick(Context context, String username, String nick, OkHttpUtils.OnCompleteListener<String> listener){
                OkHttpUtils<String> utils = new OkHttpUtils<>(context);
                utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                                .addParam(I.User.USER_NAME,username)
                                .addParam(I.User.NICK,nick)
                                .targetClass(String.class)
                                .execute(listener);
            }
    public static void updateAvatar(Context context, String username, File file, OkHttpUtils.OnCompleteListener<String> listener){
                OkHttpUtils<String> utils = new OkHttpUtils<>(context);
                utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                                .addParam(I.NAME_OR_HXID,username)
                                .addParam(I.AVATAR_TYPE,I.AVATAR_TYPE_USER_PATH)
                                .addFile2(file)
                                .targetClass(String.class)
                                .post()
                                .execute(listener);
            }
}

