package com.example.videocall

import android.app.Application
import android.content.Context
import android.content.Intent
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videocall.databinding.ActivityMainBinding
import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService

class MainActivity : AppCompatActivity() {
    val binding by lazy{ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.apply {
            startBtn.setOnClickListener {
                val stringId = editID.text.toString().trim()
                if (stringId.isEmpty()){
                    return@setOnClickListener
                }
                startService(stringId)
                startActivity(Intent(this@MainActivity,CallActivity::class.java).putExtra("userId",stringId))
            }
        }
    }

    fun startService( userId:String) {
        val application: Application = getApplication() // Replace with your Android's application context
        val appID: Long = 401695615 // Replace with your App ID
        val appSign: String = "5547e335b3d9dece68f310c41ae6e3af8957dab2787da2cd6c05268aa4f34f27" // Replace with your App Sign
        val userName: String = userId // Replace with your User Name

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true
        val notificationConfig = ZegoNotificationConfig()
        notificationConfig.sound = "zego_uikit_sound_call"
        notificationConfig.channelID = "CallInvitation"
        notificationConfig.channelName = "CallInvitation"
        ZegoUIKitPrebuiltCallInvitationService.init(
            application ,
            appID,
            appSign,
            userId,
            userName,
            callInvitationConfig
        );
    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }
}