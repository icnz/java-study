package com.java.apps.media.jave2;

import ws.schild.jave.*;

import java.io.File;

/**
 * https://github.com/a-schild/jave2
 * 提取音频WAV文件并生成128 kbit / s，立体声，44100 Hz MP3文件
 */
public class Jave2Example03 {
    public static void main(String[] args) {
        // 源
        File source = new File("source.avi");
        // 目标
        File target = new File("target.mp3");
        // 音频属性
        AudioAttributes audio = new AudioAttributes();
        // PGM编码
        audio.setCodec("libmp3lame");
        // 音频比特率
        audio.setBitRate(128000);
        // 声道
        audio.setChannels(2);
        // 采样率
        audio.setSamplingRate(44100);
        // 转码属性
        EncodingAttributes attrs = new EncodingAttributes();
        // 转码格式
        attrs.setFormat("mp3");
        // 设置音频属性
        attrs.setAudioAttributes(audio);
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
