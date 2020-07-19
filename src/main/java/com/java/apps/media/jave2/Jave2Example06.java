package com.java.apps.media.jave2;

import ws.schild.jave.*;

import java.io.File;

/**
 * https://github.com/a-schild/jave2
 * 适合智能手机的视频
 */
public class Jave2Example06 {
    public static void main(String[] args) {
        // 源
        File source = new File("source.avi");
        // 目标
        File target = new File("target.3gp");
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        // PGM编码
        audio.setCodec("libfaac");
        // 音频比特率
        audio.setBitRate(128000);
        // 声道
        audio.setChannels(2);
        // 采样率
        audio.setSamplingRate(44100);
        // 视频属性
        VideoAttributes video = new VideoAttributes();
        // 视频编码
        video.setCodec("mpeg4");
        // 视频比特率
        video.setBitRate(160000);
        // 帧率
        video.setFrameRate(15);
        // 视频尺寸
        video.setSize(new VideoSize(176, 144));
        // 转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        // 转码格式
        attrs.setFormat("3gp");
        // 设置音频属性
        attrs.setAudioAttributes(audio);
        // 设置视频属性
        attrs.setVideoAttributes(video);
        // 创建解码器
        Encoder encoder = new Encoder();
        try {
            // 转码
            encoder.encode(new MultimediaObject(source), target, attrs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }
}
