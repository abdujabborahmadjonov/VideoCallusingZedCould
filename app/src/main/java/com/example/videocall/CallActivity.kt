package com.example.videocall

import android.R.attr.button
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.videocall.databinding.ActivityCallBinding
import com.example.videocall.databinding.ActivityMainBinding
import com.zegocloud.uikit.service.defines.ZegoUIKitUser


class CallActivity : AppCompatActivity() {
    val binding by lazy { ActivityCallBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userId = intent.getStringExtra("userId")
        binding.apply {
           editID.addTextChangedListener(object :TextWatcher{
               override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

               }

               override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    var targetUserId = editID.text.toString().trim()
                   setVoiceCall(targetUserId)
                   setVideoCall(targetUserId)
               }

               override fun afterTextChanged(p0: Editable?) {

               }
           })
        }
    }
    fun setVoiceCall(targetUserId:String){
        binding.videoCallBtn.setIsVideoCall(false)
        binding.videoCallBtn.resourceID = "zegouikit_call"
        binding.videoCallBtn.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserId)))
    }
    fun setVideoCall(targetUserId: String){
        binding.videoCallBtn.setIsVideoCall(true)
        binding.videoCallBtn.resourceID = "zegouikit_call"
        binding.videoCallBtn.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserId)))
    }
}