package com.java.apps.media.jave2;

import ws.schild.jave.*;

import java.io.File;

/**
 * https://github.com/a-schild/jave2
 * 从通用AVI到类似YouTube的FLV电影，并带有嵌入式MP3音频流的转换
 */
public class Jave2Example01 {
    public static void main(String[] args) {
        // 源
        File source = new File("source.avi");
        // 目标
        File target = new File("target.flv");
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        // PGM编码
        audio.setCodec("libmp3lame");
        // 音频比特率
        audio.setBitRate(64000);
        // 声道
        audio.setChannels(1);
        // 采样率
        audio.setSamplingRate(22050);
        // 视频属性
        VideoAttributes video = new VideoAttributes();
        // 视频编码
        video.setCodec("flv");
        // 视频比特率
        video.setBitRate(160000);
        // 帧率
        video.setFrameRate(15);
        // 视频尺寸
        video.setSize(new VideoSize(400, 300));
        // 转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        // 转码格式
        attrs.setFormat("flv");
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
