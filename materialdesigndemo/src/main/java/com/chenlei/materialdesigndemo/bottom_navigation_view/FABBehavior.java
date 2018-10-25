package com.chenlei.materialdesigndemo.bottom_navigation_view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.viewpager.widget.ViewPager;

public class FABBehavior extends CoordinatorLayout.Behavior<FloatingActionButton> {
    public FABBehavior() {
        super();
    }

    public FABBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return dependency instanceof ViewPager;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, FloatingActionButton child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }
    private boolean offsetFlag=true;
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dx, int dy, int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        Log.e("Test",""+dy+","+offsetFlag);


        if(dy>20){
            if(offsetFlag) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(child, "alpha", child.getHeight(),1,0);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                child.setClickable(false);
                offsetFlag = false;
                Log.e("============", "隐藏" + offsetFlag);
            }
        }
        if(dy<-50){
            if(!offsetFlag) {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(child, "alpha", 0,1);
                objectAnimator.setDuration(1000);
                objectAnimator.start();
                child.setClickable(true);
                offsetFlag = true;
                Log.e("============","显示"+offsetFlag);
            }
        }
    }
}
