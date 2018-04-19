// pages/me/me.js
import * as app_config from '../../config/app_config.js'
import * as account_apis from '../../apis/account_apis.js'
import * as error_const from '../../config/error_const.js'
import * as api_handler from '../../apis/api_handler.js'

const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    hasLoggedIn: false,
    user_info: {
      avatarUrl: "",
      nickName: ""
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    if (app.globalData.user_info) {
      this.setData({
        hasLoggedIn: true,
        user_info: app.globalData.user_info
      })
    }
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  },

  login_success_cb: function (login_info) {
    app.globalData.token = login_info.token
    app.globalData.user_info = login_info.info

    this.setData({
      hasLoggedIn: true,
      user_info: app.globalData.user_info
    })
  },

  login_fail_cb: function (code, fail_resp) {
    if (fail_resp.err_value != error_const.account_service.WechatAccountNotExisted) {
      api_handler.server_error_proc(fail_resp)
      return;
    }

    if (!app.globalData.wechat_user_info) {
      app.userInfoReadyCallback = wechat_user_info => this.register(code, wechat_user_info)
      return;
    }

    this.register(code, app.globalData.wechat_user_info)
  },

  login: function () {
    wx.login({
      success: res => {
        account_apis.wechat_login(
          res.code,
          this.login_success_cb,
          fail_resp => this.login_fail_cb(res.code, fail_resp)
        )
      }
    })
  },

  register: function (code, wechat_user_info) {
    // register
    let reg_info = new account_apis.WechatRegInfo(
      code,
      wechat_user_info.nickName,
      wechat_user_info.avatarUrl,
      wechat_user_info.gender,
      wechat_user_info.city
    )

    account_apis.wechat_reg(
      reg_info,
      success_resp => this.login_success_cb(success_resp)
    )
  }
})