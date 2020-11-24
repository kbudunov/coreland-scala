package com.kbudunov.lesson.core.exception

import java.io.IOException

import javax.sound.sampled.{
  LineUnavailableException,
  UnsupportedAudioFileException
}

object ThrowsExample extends App {

  //for method which will be invoke from Java
  @throws(classOf[IOException])
  @throws(classOf[LineUnavailableException])
  @throws(classOf[UnsupportedAudioFileException])
  def playSoundFileWithJavaAudio: Unit = {
    // exception throwing code here ...
  }

  //Scala doesn’t require that methods declare that exceptions can be thrown,
  //and it also doesn’t require calling methods to catch them.
}
