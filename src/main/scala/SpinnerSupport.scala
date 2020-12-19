/*
 * Copyright 2020 Philipp Dörfler
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package io.doerfler.console

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole

trait SpinnerSupport {
  AnsiConsole.systemInstall()
  private[this] object monitor

  object Spinner {
    case class show(message: String) {
      def whileWaitingFor[T](f: Future[T])(implicit ec: ExecutionContext): Unit = {
        val f2 = f andThen { case _ => monitor synchronized monitor.notifyAll }
        val stream = try { AnsiConsole.err() } catch { case cce: ClassCastException => System.err } // Workaround for using jansi with native-image
        var i = 0
        val spinner = Array('\\', '|', '/', '-')
        stream.print(f"$message%s  ")
        stream.flush()
        while (! f.isCompleted) {
          stream.print("\b")
          stream.print(spinner(i))
          stream.flush()
          i = (i + 1) % spinner.size
          monitor synchronized { if (! f2.isCompleted) monitor wait 75 }
        }
        stream.print(Ansi.ansi().eraseLine(Ansi.Erase.BACKWARD))
        stream.print("\r")
        stream.flush()
      }
    }
  }
}