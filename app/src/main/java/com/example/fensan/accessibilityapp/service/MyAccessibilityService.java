package com.example.fensan.accessibilityapp.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * 这个服务是不需要你在activity里去开启的，属于系统级别辅助服务 需要在设置里去手动开启 和我们平常app里
 * 经常使用的service 是有很大不同的 非常特殊
 * 你可以在 \sdk\samples\android-23\legacy\ApiDemos 这样的目录下 找到这个工程 这个工程下面有一个accessibility
 * 包 里面有关于这个服务的demo 当然他们那个demo 非常复杂，但是信息量很大，有兴趣深入研究的同学可以多看demo
 * 我这里只实现最基本的功能 且没有做冗余和异常处理，只包含基础功能，不能作为实际业务上线！
 */
public class MyAccessibilityService extends AccessibilityService {
    public MyAccessibilityService() {
    }

    /**
     * AccessibilityService 这个服务可以关联很多属性，这些属性 一般可以通过代码在这个方法里进行设置，
     * 我这里偷懒 把这些设置属性的流程用xml 写好 放在manifest里，如果你们要使用的时候需要区分版本号
     * 做兼容，在老的版本里是无法通过xml进行引用的 只能在这个方法里手写那些属性 一定要注意.
     * 同时你的业务如果很复杂比如需要初始化广播啊之类的工作 都可以在这个方法里写。
     */
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
    }

    /**
     * 当你这个服务正常开启的时候，就可以监听事件了，当然监听什么事件，监听到什么程度 都是由给这个服务的属性来决定的，
     * 我的那些属性写在xml里了。
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        /**
         * 事件是分很多种的，我这里是最简单的那种，只演示核心功能，如果要做成业务上线 这里推荐一个方法可以快速理解这里的type属性。
         * 把这个type的int 值取出来 并转成16进制，然后去AccessibilityEvent 源码里find。顺便看注释 ，这样是迅速理解type类型的方法
         */
        final int eventType = event.getEventType();


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            switch (eventType) {
                case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                    //这个地方没什么好说的 你就理解成 找到当前界面 包含有安装 这个关键词的 所有节点就可以了。返回这些节点的list
                    //注意这里的find 其实是contains的意思，比如你界面上有2个节点，一个节点内容是安装1 一个节点内容是安装2，那这2个节点是都会返回过来的
                    //除了有根据Text找节点的方法 还有根据Id找节点的方法。考虑到众多手机rom都不一样，这里需要大家多测试一下，有的rom packageInstall
                    //定制的比较深入，可能和官方rom里差的很远 这里就要做冗余处理，可以告诉大家一个小技巧 你就把这些rom的 安装器打开 然后
                    //通过ddms里 看view结构的按钮 直接进去看就行了，可以直接看到那个界面属于哪个包名，也可以看到你要捕获的那个按钮的id是什么 很方便！
                    List<AccessibilityNodeInfo> list  = event.getSource().findAccessibilityNodeInfosByText("确定");

                    if (null != list) {
                        for (AccessibilityNodeInfo info : list) {
                            if (info.getText().toString().equals("确定")) {
                                //找到你的节点以后 就直接点击他就行了
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                    List<AccessibilityNodeInfo> list1 = event.getSource().findAccessibilityNodeInfosByText("安装");
                    if (null != list1) {
                        for (AccessibilityNodeInfo info : list1) {
                            if (info.getText().toString().equals("安装")) {
                                //找到你的节点以后 就直接点击他就行了
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }

                    List<AccessibilityNodeInfo> list2 = event.getSource().findAccessibilityNodeInfosByText("打开");
                    if (null != list2) {
                        for (AccessibilityNodeInfo info : list2) {
                            if (info.getText().toString().equals("打开")) {
                                //找到你的节点以后 就直接点击他就行了
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }

                    List<AccessibilityNodeInfo> list3 = event.getSource().findAccessibilityNodeInfosByText("下一步");
                    if (null != list3) {
                        for (AccessibilityNodeInfo info : list3) {
                            if (info.getText().toString().equals("下一步")) {
                                //找到你的节点以后 就直接点击他就行了
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }

                    List<AccessibilityNodeInfo> list4 = event.getSource().findAccessibilityNodeInfosByText("升级");
                    if (null != list4) {
                        for (AccessibilityNodeInfo info : list4) {
                            if (info.getText().toString().equals("升级")) {
                                //找到你的节点以后 就直接点击他就行了
                                info.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onInterrupt() {

    }
}