package com.java.apps.media.jave2;

import ws.schild.jave.*;

import java.io.File;

/**
 * https://github.com/a-schild/jave2
 * 生成带有MPEG 4/DivX视频和OGG Vorbis音频的AVI
 */
public class Jave2Example05 {
    public static void main(String[] args) {
        // 源
        File source = new File("source.avi");
        // 目标
        File target = new File("target.avi");
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        // PGM编码
        audio.setCodec("libvorbis");
        // 视频属性
        VideoAttributes video = new VideoAttributes();
        // 视频编码
        video.setCodec("mpeg4");
        // 标签
        video.setTag("DIVX");
        // 视频比特率
        video.setBitRate(160000);
        // 帧率
        video.setFrameRate(30);
        // 转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        // 转码格式
        attrs.setFormat("mpegvideo");
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
