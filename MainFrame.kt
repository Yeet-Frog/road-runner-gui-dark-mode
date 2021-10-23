package com.acmerobotics.roadrunner.gui

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.Toolkit
import java.awt.event.KeyEvent
import java.awt.event.WindowEvent
import java.awt.event.WindowListener
import java.io.File
import javax.swing.*
import kotlin.system.exitProcess

private val COMMAND_MASK = Toolkit.getDefaultToolkit().menuShortcutKeyMask

/**
 * Main window for standalone GUI application.
 */
class MainFrame(
    dir: File
) : JFrame() {
    init {
        title = "Road Runner GUI Dark Mode- $dir"
        size = Dimension(1000, 800)
        defaultCloseOperation = DO_NOTHING_ON_CLOSE

        setLocationRelativeTo(null)

        val mainPanel = MainPanel()
        mainPanel.background = Color.DARK_GRAY
        mainPanel.setProjectDir(dir)

        addWindowListener(
            object : WindowListener {
override fun windowDeiconified(e: WindowEvent?) {
}

override fun windowClosing(e: WindowEvent?) {
if (mainPanel.close()) {
exitProcess(0)
}
}

override fun windowClosed(e: WindowEvent?) {
}

override fun windowActivated(e: WindowEvent?) {
}

override fun windowDeactivated(e: WindowEvent?) {
}

override fun windowOpened(e: WindowEvent?) {
}

override fun windowIconified(e: WindowEvent?) {
}
}
        )

        contentPane = JPanel()
        contentPane.layout = BorderLayout()
        mainPanel.background = Color.DARK_GRAY
        contentPane.background = Color.DARK_GRAY
        contentPane.add(mainPanel)

        val fileMenu = JMenu("File")

        val saveMenuItem = JMenuItem("Save All")
        saveMenuItem.addActionListener {
            mainPanel.saveAll()
        }
        saveMenuItem.accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_S, COMMAND_MASK)
        fileMenu.add(saveMenuItem)
        saveMenuItem.background = Color.LIGHT_GRAY
        val closeMenuItem = JMenuItem("Close")
        closeMenuItem.addActionListener {
            if (mainPanel.close()) {
                exitProcess(0)
            }
        }

        val menuBar = JMenuBar()
        menuBar.add(fileMenu)

        jMenuBar = menuBar
        menuBar.background = Color.GRAY
    }
}
