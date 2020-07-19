package com.java.apps.media.jave2;

import ws.schild.jave.*;

import java.io.File;

/**
 * https://github.com/a-schild/jave2
 * 解码一个通用的AVI文件，并使用源的相同视频流和重新编码的低质量MP3音频流创建另一个文件
 */
public class Jave2Example04 {
    public static void main(String[] args) {
        // 源
        File source = new File("source.avi");
        // 目标
        File target = new File("target.avi");
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        // PGM编码
        audio.setCodec("libmp3lame");
        // 音频比特率
        audio.setBitRate(56000);
        // 声道
        audio.setChannels(1);
        // 采样率
        audio.setSamplingRate(22050);
        // 视频属性
        VideoAttributes video = new VideoAttributes();
        // 视频编码
        video.setCodec(VideoAttributes.DIRECT_STREAM_COPY);
        // 转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        // 转码格式
        attrs.setFormat("avi");
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
