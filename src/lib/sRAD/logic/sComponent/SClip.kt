package lib.sRAD.logic.sComponent

import java.io.File
import javax.sound.sampled.*

class SClip {
    
    private val audioClip: Clip
    private val audioStream: AudioInputStream

    //clip method
    constructor(path: String) {
        //create an AudioInputStream from a given sound file
        val audioFile = File(path)
        audioStream = AudioSystem.getAudioInputStream(audioFile)

        //acquire audio format and create a DataLine.Info object
        val format = audioStream.format
        val info = DataLine.Info(Clip::class.java, format)

        //obtain the Clip
        audioClip = AudioSystem.getClip()
        audioClip.open(audioStream)
    }

    fun play() {
        object: Thread(
            Runnable {
                audioClip.framePosition = 0
                audioClip.start()
            }
        ){}.start()
    }

}