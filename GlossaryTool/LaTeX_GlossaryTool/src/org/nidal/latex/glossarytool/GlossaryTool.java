/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nidal.latex.glossarytool;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.swing.AbstractAction;
import javax.swing.Action;
import static javax.swing.Action.ACCELERATOR_KEY;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;
import javax.swing.text.TextAction;
import org.fife.rsta.ui.CollapsibleSectionPanel;
import org.fife.rsta.ui.SizeGripIcon;
import org.fife.rsta.ui.search.FindDialog;
import org.fife.rsta.ui.search.FindToolBar;
import org.fife.rsta.ui.search.ReplaceDialog;
import org.fife.rsta.ui.search.ReplaceToolBar;
import org.fife.rsta.ui.search.SearchEvent;
import static org.fife.rsta.ui.search.SearchEvent.Type.FIND;
import static org.fife.rsta.ui.search.SearchEvent.Type.MARK_ALL;
import static org.fife.rsta.ui.search.SearchEvent.Type.REPLACE;
import static org.fife.rsta.ui.search.SearchEvent.Type.REPLACE_ALL;
import org.fife.rsta.ui.search.SearchListener;
import org.fife.ui.rsyntaxtextarea.ErrorStrip;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.spell.SpellingParser;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.fife.ui.rtextarea.RecordableTextAction;
import org.fife.ui.rtextarea.SearchContext;
import org.fife.ui.rtextarea.SearchEngine;
import org.fife.ui.rtextarea.SearchResult;
import org.nidal.latex.glossarytool.UnderlineHighlighter.UnderlineHighlightPainter;

/**
 *
 * @author nidal
 */
//public class GlossaryTool extends JFrame implements ActionListener {
public class GlossaryTool extends JFrame implements SearchListener{
//    RSyntaxTextArea textArea = new RSyntaxTextArea(25,80);
 RSyntaxTextArea textArea = new RSyntaxTextArea(25,80);
 //TextArea textArea = new TextArea(25,80);

    private static RecordableTextAction cutAction;
	private static RecordableTextAction copyAction;
	private static RecordableTextAction pasteAction;
	private static RecordableTextAction deleteAction;
	private static RecordableTextAction undoAction;
	private static RecordableTextAction redoAction;
	private static RecordableTextAction selectAllAction;

         Intelligence intelligence  = new Intelligence() ;




  int flag = 0 ;
   //
  
  // String filename = "" ;
    String filename_s  = "";
    String filename_final ;
    private KeyListener k1 ;
    String applicationName = "LaTeX_GlossaryTool";
    String holdText;
    String fn;
    String dir;
    boolean textChanged = false;
   private String fileName = "";
    private CollapsibleSectionPanel csp;
  //  private RSyntaxTextArea textArea;
    private FindDialog findDialog;
    private ReplaceDialog replaceDialog;
    private ReplaceDialog add_glsDialog;
    private FindToolBar findToolBar;
    private ReplaceToolBar replaceToolBar;
    private final StatusBar statusBar;

    String glossaryFileName ;
    Clipboard clip = getToolkit().getSystemClipboard();

     ReadGlossaryFile readGlossaryFile = new ReadGlossaryFile() ;
     //PopupSubclass popupsbcls = new PopupSubclass();
    // TagHighlighter taghighlighter = new TagHighlighter();
  //   GlossaryTool glossaryTool ;

   






    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    Boolean checktagExists;

    // private Map<String,GlossaryEntryClass> fieldsMap;
    private Map<String,GlossaryEntryClass1> fieldsMap;
    public Map<String, Map> gMap ;
  //   private Map<String, String> fields ;
    private Map<String, String> repeatTags ;


    //GlossaryEntryClass gec3 ;

    List<Object> collect ;



     JTextField name_tf = new JTextField("");
        JTextField plural_tf = new JTextField("");
        JTextField tag_tf = new JTextField("");
        JTextField symbol_tf = new JTextField("");
        RSyntaxTextArea desc_Area = new RSyntaxTextArea("");


final org.nidal.latex.glossarytool.WordSearcher searcher ;
final org.nidal.latex.glossarytool.WordSearcher remove_highlight ;
//final org.nidal.latex.glossarytool.WordSearcher5 searcher5 ;

final org.nidal.latex.glossarytool.WordSearcherForSingleInstance searcherWord ;
final org.nidal.latex.glossarytool.WordSearcherForSingleInstance remove_highlightWord ;



    private JMenuBar menubar = new JMenuBar(); //menubar item
    private JPopupMenu popup = textArea.getPopupMenu();

    private JMenu glossaryMenu;
    private JMenuItem glsToolItem;
	private JMenuItem glsplToolItem;
	private JMenuItem GlsToolItem;
	private JMenuItem GlsplToolItem;
	private JMenuItem glssymbolToolItem;
//    private static RecordableTextAction toggleCurrentFoldAction;
//    private static RecordableTextAction collapseAllCommentFoldsAction;
//    private static RecordableTextAction collapseAllFoldsAction;
//    private static RecordableTextAction expandAllFoldsAction;

    private JMenu file = new JMenu(); //  File menu
    private JMenu edit = new JMenu(); // edit menu
    private JMenu tools = new JMenu(); //  tools menu
    private JMenu help = new JMenu(); //  help menu


    private JDialog d1;

    private JMenuItem newFile = new JMenuItem();  //  a new option
    private JMenuItem openFile = new JMenuItem();  // an open option
    private JMenuItem saveFile = new JMenuItem(); // a save option
    private JMenuItem saveasFile = new JMenuItem(); // a save as option
    private JMenuItem exitFile = new JMenuItem(); // and a close option!

    private JMenuItem cut = new JMenuItem();  //  a cut option
    private JMenuItem copy = new JMenuItem();  // an copy option
    private JMenuItem paste = new JMenuItem(); // a paste option
    private JMenuItem undo = new JMenuItem(); // a undo as option
    private JMenuItem redo = new JMenuItem(); // a redo option!




    //JTextcomponent from java

     JTextComponent jtc ; //= new JTextComponent();


      private JMenuItem search_and_highlight_Tool = new JMenuItem(); // a searchandhighlighttool menu option
       private JMenuItem remove_all_highlight_Tool = new JMenuItem(); // remove all highlight menu option
       private JMenuItem glossarise_All_WordsTool = new JMenuItem(); // glossarise all the words menu option
    int ctrl = getToolkit().getMenuShortcutKeyMask();
    int shift = InputEvent.SHIFT_MASK;
    KeyStroke ks = KeyStroke.getKeyStroke(KeyEvent.VK_F, ctrl | shift);

    private JMenuItem add_gls = new JMenuItem(); // a tools option

    private JMenuItem about = new JMenuItem(); // about option!
    File zip = new File("english_dic.zip");
    boolean usEnglish = true; // "false" will use British English

      public Highlighter highlighterh = new org.nidal.latex.glossarytool.UnderlineHighlighter(null);



    public GlossaryTool()  {

//Reference : https://github.com/bobbylight/RSyntaxTextArea
//reference : http://www.dreamincode.net/forums/topic/66176-creating-a-basic-notepad-application/


        initSearchDialogs();

        JPanel cp = new JPanel(new BorderLayout());
        int c = getToolkit().getMenuShortcutKeyMask();


//        fields = new HashMap<String, GlossaryEntryClass>() ;
    fieldsMap = new HashMap<>() ;
//         gmap = new HashMap<String, Map>();
    gMap = new HashMap<>();
        repeatTags = new HashMap<>();
       // fields = new HashMap<>();

        csp = new CollapsibleSectionPanel();
        cp.add(csp);

          this.setSize(500, 300);
        this.setTitle("Glossary Tool");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);



 searcher = new org.nidal.latex.glossarytool.WordSearcher(textArea);
  remove_highlight = new org.nidal.latex.glossarytool.WordSearcher(textArea);
  
  
   searcherWord = new org.nidal.latex.glossarytool.WordSearcherForSingleInstance(textArea);
  remove_highlightWord = new org.nidal.latex.glossarytool.WordSearcherForSingleInstance(textArea);
// searcher5 = new org.nidal.latex.glossarytool.WordSearcher5(textArea);



        //dictionary feature.


      //  textArea.setCodeFoldingEnabled(true);
        textArea.setMarkOccurrences(true);

        RTextScrollPane sp = new RTextScrollPane(textArea);

        //this.getContentPane().setLayout(new BorderLayout()); // the BorderLayout bit makes it fill it automatically
        //this.getContentPane().add(textArea);
        cp.add(sp);
        ErrorStrip errorStrip = new ErrorStrip(textArea);
		cp.add(errorStrip, BorderLayout.LINE_END);
        this.setContentPane(cp);


        textArea.addKeyListener(k1);

        statusBar = new StatusBar();
        cp.add(statusBar, BorderLayout.SOUTH);
        // add our menu bar into the GUI
        this.setJMenuBar(this.menubar);

        this.menubar.add(this.file);
        this.menubar.add(this.edit);
        this.menubar.add(this.tools);



        //   this.menubar.add(this.help);
        this.file.setLabel("File");

        this.edit.setLabel("Edit");
        this.tools.setLabel("Tools");
        //    this.help.setLabel("Help");

        // new
        this.newFile.setLabel("New"); // set the label of the menu item
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, c));
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });

        this.file.add(this.newFile); // add it to the "File" menu

        // open
        this.openFile.setLabel("Open"); // set the label of the menu item
        //  this.openFile.addActionListener(this); // add an action listener (so we know when it's been clicked
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    openMenuActionPerformed(evt);
                } catch (IOException ex) {
                    Logger.getLogger(GlossaryTool.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
      openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, c));
        this.file.add(this.openFile); // add it to the "File" menu

        // and the save...
        this.saveFile.setLabel("Save");
        //  this.saveFile.addActionListener(this);
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuActionPerformed(evt);
            }
        });
          saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, c));
        this.file.add(this.saveFile);


        // File Menu - SaveAs
        this.saveasFile.setLabel("Save As"); 
        saveasFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsMenuActionPerformed(evt); 
            }
        });
       // saveasFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, c));
        this.file.add(this.saveasFile); // add it to the "File" menu

       // File Menu - Exit
        this.exitFile.setLabel("Exit");
        exitFile.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuActionPerformed(evt);
            }
        });
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, c));
        this.file.add(this.exitFile);



          //Edit Menu tool

           this.undo.setLabel("Undo"); // set the label of the menu item

          this.undo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                undoEditActionPerformed(evt);
            }
        });
         this.undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, c));
          this.edit.add(this.undo); // add it to the "File" menu




          // Redo
          this.redo.setLabel("Redo"); // set the label of the menu item
         this.redo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redoEditActionPerformed(evt);
            }
        });
          this.redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, c));
          this.edit.add(this.redo); // add it to the "File" menu

          // and cut
          this.cut.setLabel("Cut");
         this.cut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutEditActionPerformed(evt);
            }
        });
          this.cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, c));
          this.edit.add(this.cut); // add it to the "File" menu

          // and copy
          this.copy.setLabel("Copy");
         this.copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyEditActionPerformed(evt);
            }
        });
          this.copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, c));
           this.edit.add(this.copy); // add it to the "File" menu

          // and paste
          this.paste.setLabel("Paste");
        this.paste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteEditActionPerformed(evt);
            }
        });
         paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, c));
          this.edit.add(this.paste); // add it to the "File" menu



        edit.addSeparator();
        edit.add(new JMenuItem(new ShowFindDialogAction()));
        edit.add(new JMenuItem(new ShowReplaceDialogAction()));


        // add highlight
        this.search_and_highlight_Tool.setLabel("Search & Highlight Word");
        search_and_highlight_Tool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glossarysearchToolMenuActionPerformed(evt);
            }
        });
       // search_and_highlight_Tool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, c));
        this.tools.add(this.search_and_highlight_Tool); // add it to the "File" menu
        
        // remove highlight
        this.remove_all_highlight_Tool.setLabel("Remove All Highlights");
        remove_all_highlight_Tool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAllHighlightToolMenuActionPerformed(evt);
            }
        });
        //search_and_highlight_Tool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, c));
        this.tools.add(this.remove_all_highlight_Tool); // add it to the "File" menu



         // and searchTool
        this.glossarise_All_WordsTool.setLabel("Glossarise all the words");
        glossarise_All_WordsTool.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                glossariseaAllWordsToolMenuActionPerformed(evt);
            }
        });
        //search_and_highlight_Tool.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, c));
        this.tools.add(this.glossarise_All_WordsTool); // add it to the "File" menu

        
       


        //from website highlight 22
//    textArea.getDocument().addDocumentListener(new DocumentListener() {
//
//      public void insertUpdate(DocumentEvent evt) {
//
//                 searcher.search(word);
//      }
//
//      public void removeUpdate(DocumentEvent evt) {
//        searcher.search(word);
//      }
//
//      public void changedUpdate(DocumentEvent evt) {
//      }
//    });

////highlighter stuff
//        textArea.setHighlighter(highlighter);
//        highlight.getDocument().addDocumentListener(new DocumentListener() {
//
//      private void updateHighlights() {
//        highlight(textArea, highlight.getText(), painter);
//      }
//
//      @Override
//      public void removeUpdate(DocumentEvent e) {
//        updateHighlights();
//      }
//
//      @Override
//      public void insertUpdate(DocumentEvent e) {
//        updateHighlights();
//      }
//
//      @Override
//      public void changedUpdate(DocumentEvent e) {
//        updateHighlights();
//      }
//    });

//        Action a = csp.addBottomComponent(ks, findToolBar);
//        a.putValue(Action.NAME, "Show Find Search Bar");
//        edit.add(new JMenuItem(a));

//ks = KeyStroke.getKeyStroke(KeyEvent.VK_H, ctrl|shift);
//        a = csp.addBottomComponent(ks, replaceToolBar);
//        a.putValue(Action.NAME, "Show Replace Search Bar");
//        edit.add(new JMenuItem(a));
/*

//Tools menu
// Add to Gls
this.add_gls.setLabel("Add to Glossary");
this.add_gls.addActionListener(this);
//this.add_gls.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
this.tools.add(this.add_gls);


//help menu
// about us
//     this.about.setLabel("About Us");
//    this.about.addActionListener(this);
//      //this.add_gls.setShortcut(new MenuShortcut(KeyEvent.VK_S, false));
//        this.help.add(this.about);

*/



//          checktagExists =readGFileObject.checkifSavedinGlossaryFile(textArea.getSelectedText());
//
//            if(!checktagExists)
//             {


//   popupsbcls.createToolsPopupMenuActions();

             popup.addSeparator();
              popup.add(new JMenuItem(new glossariseTheWordPopup())); //#6

            popup.addSeparator();
            popup.add(new JMenuItem(new glossariseAllTheWordPopup()));
            
            
            popup.addSeparator();
            popup.add(new JMenuItem(new  highlightSingleWordOccurance()));
           

            popup.addSeparator();
            popup.add(new JMenuItem(new addtoGlossaryPopup()));


          //  PopupSubclass.setPopupMenu();

         // appendGlossariecePopupMenu();



         // popupsbcls.createPopupMenu();

//         popup.addSeparator();


//glossaryMenu.add(new JMenuItem(new glsToolItemPopup()));
                //glossaryMenu.add("glsToolItem");

//		glossaryMenu.add("GlsToolItem");
//		glossaryMenu.add("GlsToolItem");
//		glossaryMenu.add("GlsplToolItem");
//                glossaryMenu.add("glssymbolToolItem");

        //glossaryMenu.setLabel("Tools");
       // popup.add(new JMenuItem(new glsToolItemPopup()));
       // popup.add(glossaryMenu);


      //  PopupSubclass.creatPopupMenu();





//        glossaryMenu.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                pasteEditActionPerformed(evt);
//            }
//        });


        //    popup.addSeparator();
         //   popup.add(new JMenuItem(new GlossariesPopup()));



           //  }



        addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });


//        k1 = new KeyAdapter() {
    textArea.addKeyListener(new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
		 // TODO add your handling code here:
        if (RTextEvent.TEXT_VALUE_CHANGED != 0) {
            if (!textChanged)
                setTitle("* " + getTitle());

            textChanged = true;
            saveFile.setEnabled(true);
		}
                }
	});

  textArea.addCaretListener(new CaretListener() {

      public void caretUpdate(CaretEvent e) {
      	 // TODO add your handling code here:
        if (RTextEvent.TEXT_VALUE_CHANGED != 0) {
            if (!textChanged)
                setTitle("* " + getTitle());

            textChanged = true;
            saveFile.setEnabled(true);
		}

      }
    });



        pack();
        setLocationRelativeTo(null);

    }



    private void addItem(Action a, ButtonGroup bg, JMenu menu) {
		JRadioButtonMenuItem item = new JRadioButtonMenuItem(a);
		bg.add(item);
		menu.add(item);
	}

    public void checkFile() {
        BufferedReader read;
        StringBuffer sb = new StringBuffer();

        try {
            read = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = read.readLine()) != null) {
                sb.append(line + "\n");
            }
            // textArea1.setText(sb.toString());
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            textArea.setText(sb.toString());

            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        } catch (IOException ioe) {
        }

    }

    private void openMenuActionPerformed(java.awt.event.ActionEvent evt) throws IOException {
        // TODO add your handling code here:
        // if (textArea1.getText().length() < 1) {
        if (textArea.getText().length() < 1) {
            FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
            fd.show();
            if (fd.getFile() != null) {
                this.fileName = fd.getDirectory() + fd.getFile();
                   this.filename_final = fd.getDirectory() + "glossary.tex" ;
                setTitle(fileName);
                checkFile();
            }
            // textArea1.requestFocus();
           // dictionarycheck();
           gMap.clear();
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            textArea.requestFocus();

        } else if (!textChanged) {
            FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
            fd.show();
            if (fd.getFile() != null) {
                this.fileName = fd.getDirectory() + fd.getFile();
                     this.filename_final = fd.getDirectory() + "glossary.tex" ;
                setTitle(fileName);
                checkFile();

            }
            // textArea1.requestFocus();

            //dictionarycheck();
            gMap.clear();
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
                  textArea.requestFocus();




        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save before exiting this application? ");

            if (confirm == JOptionPane.YES_OPTION) {
                if ("".equals(fileName)) {
                    saveAs();
                } else {
                    save(fileName);
                }

                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                fd.show();

                if (fd.getFile() != null) {
                    this.fileName = fd.getDirectory() + fd.getFile();
                    // this.filename_final = fd.getDirectory() + fd.getFile().replaceFirst(".tex", ".gls");
                     this.filename_final = fd.getDirectory() + "glossary.tex" ;
                    setTitle(fileName);
                    checkFile();

                }
                //   textArea1.requestFocus();
                gMap.clear();
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
                textArea.requestFocus();

            } else if (confirm == JOptionPane.NO_OPTION) {

                FileDialog fd = new FileDialog(this, "Choose File", FileDialog.LOAD);
                fd.show();

                if (fd.getFile() != null) {
                    this.fileName = fd.getDirectory() + fd.getFile();
                        this.filename_final = fd.getDirectory() + "glossary.tex" ;
                    setTitle(fileName);
                    checkFile();
                    gMap.clear();
                }   
            }
        }

        //FileDialog option to select the default file opener
   selectGlossaryFile();
    }

    private void dictionarycheck() throws IOException {
             SpellingParser parser = SpellingParser.createEnglishSpellingParser(zip, usEnglish);
             //SpellingParser parser = SpellingParser.createEnglishSpellingParser(glossaryFileName, usEnglish);
textArea.addParser(parser);
    }

    private void selectGlossaryFile() throws IOException {
        // TODO add your handling code here:


            FileDialog fd = new FileDialog(this, "Select the Glossary File", FileDialog.LOAD);
            fd.show();
            if (fd.getFile() != null) {
                glossaryFileName = fd.getDirectory() + fd.getFile();
             //   checkFile();
             readGlossaryFile.addtoArrayList(glossaryFileName);


            }

   }

    private void saveAsMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        saveAs();
    }

    private void add_glsMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        saveAs();
    }

    private void saveMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (fileName.equals("")) {
            saveAs(); } 
        else {           
            save(fileName);
        }
    }


    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        newFile();
    }

    private void exitMenuActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        // if ("".equals(textArea1.getText())) {
        if ("".equals(textArea.getText())) {
            System.exit(0);
        } else if (!textChanged) {
            System.exit(0);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to save before exiting this application");
            if (confirm == JOptionPane.YES_OPTION) {
                if (fileName.equals("")) {
                    saveAs();
                } else {
                    save(fileName);
                }

            }
            if (confirm == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
        }
    }

    private void undoEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
       //PopupSubclass.undoAction.isEnabled();
    }

    private void redoEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        redoAction.isEnabled();
    }


    private void cutEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
       // RTextAreaEditorKit.CutAction();
      //  cutAction.isEnabled();
       // RSyntaxTextArea.CUT_ACTION

    }

    private void copyEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        copyAction.isEnabled();


    }
    private void pasteEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
       pasteAction.isEnabled();
      
    }


    private void glossarysearchToolMenuActionPerformed(java.awt.event.ActionEvent evt) {
      //String word1 = "(?<!\\S)"+word+"(?!\\S)" ;
      
         intelligence.checkavailabilityin_Map1();
    }
   
    private void removeAllHighlightToolMenuActionPerformed(java.awt.event.ActionEvent evt) {
      //String word1 = "(?<!\\S)"+word+"(?!\\S)" ;
         remove_highlight.removehighlighter();
    }
    
     private void glossariseaAllWordsToolMenuActionPerformed(java.awt.event.ActionEvent evt) {
      //String word1 = "(?<!\\S)"+word+"(?!\\S)" ;
         intelligence.checkavailabilityin_Map3();
    }




    private void formWindowClosing(java.awt.event.WindowEvent evt) {
        // TODO add your handling code here:
        //  if ("".equals(textArea1.getText())) {
        if ("".equals(textArea.getText())) {
            System.exit(0);
        } else if (!textChanged) {
            System.exit(0);
        } else {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to save before exiting this application ?");
            if (confirm == JOptionPane.YES_OPTION) {
                if (fileName.equals("")) {
                    saveAs();
                } else {
                    save(fileName);
                }
                System.exit(0);
            }
            if (confirm == JOptionPane.NO_OPTION) {
                System.exit(0);

            }
        }
    }

    private void saveAs() {
               
        FileDialog fd = new FileDialog(GlossaryTool.this, "Save As", FileDialog.SAVE);
        fd.show();
        
      
        if (fd.getFile() != null) {
            fn = fd.getFile();
            dir = fd.getDirectory();
           fileName = dir + fn + ".tex";
            // this.filename = dir + fn;
            // this.filename_s = dir + fn ;
               this.filename_final = fd.getDirectory() + "glossary.tex" ;

            setTitle(fileName);

            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(fileName));
                holdText = textArea.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText));

                while ((holdText = br.readLine()) != null) {
                    d.writeBytes(holdText + "\r\n");
                    d.close();

        
                }

                
            } catch (Exception e) {
                System.out.println("File not found");
            } 


            //textArea1.requestFocus();
            //textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
            textArea.requestFocus(); 
            save(fileName);
        }
               
    }

    private void saveGlossary_new_entry() {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
       // FileDialog fd = new FileDialog(GlossaryTool.this, "Save", FileDialog.SAVE);
      //  fd.show();
        //if (fd.getFile() != null) {
           // fn = fd.getFile();
           // dir = fd.getDirectory();
            //filename = dir + fn + ".gls";

          //  String filename_gls = filename_final + ".gls";

           // setTitle(filename);

            try {
                DataOutputStream d = new DataOutputStream(new FileOutputStream(filename_final));
                System.out.println(filename_final);
//                holdText = textArea1.getText();
                holdText = textArea.getText();
                BufferedReader br = new BufferedReader(new StringReader(holdText));

                while ((holdText = br.readLine()) != null) {
                    d.writeBytes(holdText + "\r\n");
                    d.close();

                }
            } catch (Exception e) {
                System.out.println("File not found");
            }

            //textArea1.requestFocus();
           // textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_LATEX);
           // textArea.requestFocus();

           try {
            FileWriter out;
           // out = new FileWriter(fn);
            out = new FileWriter(filename_final);
            out.write(textArea.getText());
            out.close();

        } catch (Exception err) {
            System.out.println("Error: " + err);
        }
      //  }

    }

    private void save(String filename) {
        setTitle(applicationName + " " + filename);
        try {
            FileWriter out ;
            out = new FileWriter(filename) ;
            textArea.requestFocus(); 
            out.write(textArea.getText());
            out.close();
//            FileWriter w = new FileWriter(fileName);	
//            textArea.write(w);
//            w.close();

        } catch (Exception err) {
            System.out.println("Error: " + err); }

        textChanged = false;
        saveFile.setEnabled(false);
        writeto_GlossaryFile();
        gMap.clear();
    }

    
 
    private void newFile() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        if (textArea1.getText().length() < 1) {

        if (textArea.getText().length() < 1) {
            setTitle("Untitled-" + applicationName);
//            textArea1.setText("");
            textArea.setText("");
             fileName = "";
            textChanged = false;

        } else if (!textChanged) {
            setTitle("Untitled-" + applicationName);
//            textArea1.setText("");
            textArea.setText("");
             fileName = "";
            textChanged = false;

        } else {
            int confirm = JOptionPane.showConfirmDialog(null, "Do you want to save the existing file before opening a new one?");

            if (confirm == JOptionPane.YES_OPTION) {
                if ("".equals(fileName)) {
                    saveAs();
                } else {
                    save(fileName);
                }

                setTitle(applicationName);
                fileName = "";
                //  textArea1.setText("");
                textArea.setText("");
                textChanged = false;

            } else if (confirm == JOptionPane.NO_OPTION) {
                setTitle(applicationName);
                //  textArea1.setText("");
                textArea.setText("");
                textChanged = false;

            }
        }
    }

    // private void textAreaTextValueChanged(TextEvent evt) {
     private void textAreaTextValueChanged(org.nidal.latex.glossarytool.RTextEvent evt) {
        // TODO add your handling code here:
        if (RTextEvent.TEXT_VALUE_CHANGED != 0) {
            if (!textChanged)
                setTitle("* " + getTitle());

            textChanged = true;
            saveFile.setEnabled(true);
        }
    }

    public String getSelectedText() {
         checktagExists = false ;
        return textArea.getSelectedText();
    }

    public void initSearchDialogs() {

        findDialog = new FindDialog(this, this);
        replaceDialog = new ReplaceDialog(this, this);

        // This ties the properties of the two dialogs together (match case,
        // regex, etc.).
        SearchContext context = findDialog.getSearchContext();
        replaceDialog.setSearchContext(context);

        // Create tool bars and tie their search contexts together also.
      //  findToolBar = new FindToolBar((SearchListener) this);
        //findToolBar.setSearchContext(context);
        //replaceToolBar = new ReplaceToolBar((SearchListener) this);
        //replaceToolBar.setSearchContext(context);

    }

    @Override
    public void searchEvent(SearchEvent e) {

        SearchEvent.Type type = e.getType();
        SearchContext context = e.getSearchContext();
        SearchResult result = null;

        switch (type) {
            default: // Prevent FindBugs warning later
            case MARK_ALL:
                result = SearchEngine.markAll(textArea, context);
                break;
            case FIND:
                result = SearchEngine.find(textArea, context);
                if (!result.wasFound()) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                }
                break;
            case REPLACE:
                result = SearchEngine.replace(textArea, context);
                if (!result.wasFound()) {
                    UIManager.getLookAndFeel().provideErrorFeedback(textArea);
                }
                break;
            case REPLACE_ALL:
                result = SearchEngine.replaceAll(textArea, context);
                JOptionPane.showMessageDialog(null, result.getCount()
                        + " occurrences replaced.");
                break;
        }

        String text = null;
        if (result.wasFound()) {
            text = "Text found; occurrences marked: " + result.getMarkedCount();
        } else if (type == SearchEvent.Type.MARK_ALL) {
            if (result.getMarkedCount() > 0) {
                text = "Occurrences marked: " + result.getMarkedCount();
            } else {
                text = "";
            }
        } else {
            text = "Text not found";
        }
        statusBar.setLabel(text);

    }

    //to add \gls{text}
    private void addglsprefix(){
        String text_selected = textArea.getSelectedText();
         String text_replacement = "\\gls{" + text_selected + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
    }


      private void createAndShowadd_glsDialog()
    {

        //https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/uiswing/examples/layout/SpringBoxProject/src/layout/SpringBox.java
        JPanel dpan=new JPanel();
          JPanel dpan1 =new JPanel();
           JPanel dpanel_master =new JPanel();
      //  Container dpan = d1.getContentPane();



        // dpan.setLayout(new BorderLayout());
          dpan.setLayout(new SpringLayout());
         //  dpan.setLayout(new GridLayout(5,2));
          // dpan.setLayout(new FlowLayout());
           dpan1.setLayout(new FlowLayout());
          //  dpan1.setLayout(new GridLayout(0,3));

          dpan.setMaximumSize(new Dimension(520, 400));
            dpan1.setMaximumSize(new Dimension(520, 150));
           // dpan1.setLayout(null);
                dpanel_master.setLayout(new BorderLayout());
               // dpanel_master.setLayout(new GridLayout(2,0));
       //dpan.setLayout(new GridBagLayout());

     //  GridBagConstraints c = new GridBagConstraints();
  //       if(shouldFill) {
                //natural height, maximum width
             //   c.fill = GridBagConstraints.HORIZONTAL;
//}
       // setDefaultCloseOperation(EXIT_ON_CLOSE);

     //  d1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
       // setLayout(new FlowLayout());

        // Must be called before creating JDialog for
        // the desired effect
        JDialog.setDefaultLookAndFeelDecorated(true);

        // A perfect constructor, mostly used.
        // A dialog with current frame as parent
        // a given title, and modal
        d1=new JDialog(this,"Add to Glossary",true);

        // Set size


//        if (RIGHT_TO_LEFT)
//            dpan.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
//
        // Set some layout
      //  d1.setLayout(new FlowLayout());

//    current_y += 30;
 JLabel selectedword_label = new JLabel("Selected Word");
JTextField selectedword_tf = new JTextField(textArea.getSelectedText());
selectedword_tf.setEditable(false);

JLabel tag_label = new JLabel("Tag");

   JTextField tag_tf = new JTextField("");
   tag_label.setLabelFor(tag_tf);


     JLabel name_label = new JLabel("Name");
//
    JTextField name_tf = new JTextField("");
//
    JLabel plural_label = new JLabel("Plural");
//
    JTextField plural_tf = new JTextField("");
//
    JLabel symbol_label = new JLabel("Symbol");
//
    JTextField symbol_tf = new JTextField("");
//
    JLabel desc_label = new JLabel("Desciption");
//
     RSyntaxTextArea desc_Area = new RSyntaxTextArea("");

    JButton cancel_gls = new JButton("Cancel");

     JButton save_gls = new JButton("Save");
     JButton clear_gls = new JButton("Clear");

     tag_label.setLabelFor(tag_tf);
      // name_label.setLabelFor(name_tf);
         //plural_label.setLabelFor(plural_tf);
        //   symbol_label.setLabelFor(symbol_tf);
          //   desc_label.setLabelFor(desc_Area);


           dpan.add(selectedword_label);
     dpan.add(selectedword_tf);
       dpan.add(tag_label);
     dpan.add(tag_tf);
     dpan.add(name_label);
       dpan.add(name_tf);
     dpan.add(plural_label);
      dpan.add(plural_tf);
     dpan.add(symbol_label);
     dpan.add(symbol_tf);
      dpan.add(desc_label);
     dpan.add(desc_Area);

     dpan1.add(cancel_gls);
      cancel_gls.setPreferredSize(new Dimension(120, 50));
    dpan1.add(save_gls);
     save_gls.setPreferredSize(new Dimension(120, 50));
//     dpan1.add(clear_gls,BorderLayout.LINE_END);
    dpan1.add(clear_gls);
      clear_gls.setPreferredSize(new Dimension(120, 50));

       //Lay out the buttons in one row and as many columns
        //as necessary, with 6 pixels of padding all around.

        //if the word already exists then it disables the save button
        checktagExists =readGlossaryFile.checkifSavedinGlossaryFile(textArea.getSelectedText());

            if(checktagExists)
             {
               save_gls.setVisible(false);
             }


//      name_tf.setText("");
//      plural_tf.setText("");
//      symbol_tf.setText("");
//       desc_Area.setText("");

        cancel_gls.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {


                   d1.dispose();

            }
        });

         save_gls.addActionListener(new ActionListener() {



            @Override
            public void actionPerformed(ActionEvent e) {

                String tag_gls = tag_tf.getText().trim();


                String name_gls = name_tf.getText().trim();;
             //   JTextField name_tf = new JTextField("");
                String symbol_gls = symbol_tf.getText().trim();
                String plural_gls = plural_tf.getText().trim();
                String desc_gls = desc_Area.getText().trim();
                GlossaryEntryClass gec  ; //= new GlossaryEntryClass(name_gls,symbol_gls,plural_gls,desc_gls);

                //sort use
                String all_gls ;
               // createglossaryentryData(tag_gls,gec);
                //gec = new GlossaryEntryClass(name_gls,symbol_gls,plural_gls,desc_gls);


              System.out.println(tag_gls+""+name_gls+""+symbol_gls+""+plural_gls+""+desc_gls);

              //fields.put(tag_gls, new GlossaryEntryClass());
                 // fields.put(tag_gls, gec);
                //  createglossaryentryData(tag_gls,gec);
             //  gmap.put(tag_gls, fields);

              // createglossaryentryData(fields);
              // gmap.put(tag_gls, fields);
               //Map<String, GlossaryEntryClass>

//               fieldsMap.put(tag_gls, new GlossaryEntryClass1(tag_gls,name_gls,symbol_gls,plural_gls,desc_gls));



              GlossaryEntryClass1  glossaryentryclass1 = new GlossaryEntryClass1(tag_gls.toLowerCase(),name_gls,symbol_gls,plural_gls,desc_gls);
          //    gMap.put(tag_gls, new GlossaryEntryClass1(tag_gls,name_gls,symbol_gls,plural_gls,desc_gls));

         //   gmap.put(tag_gls, GlossaryEntryClass);
          //      ? what is the point of fields class find out ?

          // System.out.println(GlossaryEntryClass1.this.get("Some Key"));

                //System.out.println(gMap);
                         System.out.println(gMap);
                       //  hjh


                // System.out.println("Tag" + );



         //       saveGlossary_new_entry();

      //  addglsprefix();
             String replacement =   intelligence.checkavailabilityin_Map(textArea.getSelectedText());
             intelligence.repeattags_add(textArea.getSelectedText(),replacement) ;
        readGlossaryFile.addtoArrayListFromDialogSave(tag_gls);
                  d1.dispose();

            }
        });

          clear_gls.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

   //  dpan.add(tag_tf);
     // dpan.add(name_tf);

    //  dpan.add(plural_tf);
      name_tf.setText("");
      plural_tf.setText("");
       symbol_tf.setText("");
       desc_Area.setText("");

             //     d1.dispose();

            }
        });
         int count = dpan.getComponentCount()/2 ;
         System.out.println(count);

//     SpringUtilities.makeCompactGrid(dpan, 6,dpan.getComponentCount(),6, 6, 6, 6);
     SpringUtilities.makeCompactGrid(dpan, count,2,10, 10, 10, 10);
     // SpringUtilities.makeCompactGrid(dpan1, 1,3,10, 10, 10, 10);
     dpanel_master.add(dpan,BorderLayout.CENTER);
      dpanel_master.add(dpan1, BorderLayout.SOUTH);
     //  SpringUtilities.makeCompactGrid(dpanel_master, dpanel_master.getComponentCount(),1,6, 6, 6, 6);


      d1.add(dpanel_master);

         d1.setSize(520,500);
         d1.setMinimumSize(new Dimension(520,400));
       // setSize(80,20);
  d1.setLocationRelativeTo(null);


        d1.setVisible(true);
    }



//  GlossaryEntryClass gec10 = new GlossaryEntryClass();
//Hashtable hashTables = new Hashtable();
//  HashMap<Integer, GlossaryEntryClass> hashMap = hashTables.buildHash(gec10);
//
//public static HashMap<Integer, GlossaryEntryClass> buildHash(GlossaryEntryClass[] students) {
//            HashMap<Integer, GlossaryEntryClass> hashMap = new HashMap<Integer, GlossaryEntryClass>();
//            for (GlossaryEntryClass s : students) hashMap.put(s.getId(), s);
//            return hashMap;
//       }

//  public void createglossaryentryData(String tag, GlossaryEntryClass gec5) {
//
//
//      String tag_gls = tag_tf.getText();
//      String name_gls = name_tf.getText();
//      String symbol_gls = symbol_tf.getText();
//      String plural_gls = plural_tf.getText();
//      String desc_gls = desc_Area.getText();
//
//      fieldsMap.put(tag_gls, new GlossaryEntryClass(tag_gls, name_gls, symbol_gls, plural_gls, desc_gls));   //innerMap
//      gMap.put(tag_gls, fieldsMap); //OuterMap
//
//      //todo : (delete) : check output of the OuterMap
//      System.out.println(gMap);
//
//}



  //class GlossaryEntryClass implements Map<String,String>  {
  // class GlossaryEntryClass implements Map<String,String>  {

    @SuppressWarnings("serial")
     public class GlossaryEntryClass1 {


      //private Map<String,String> fields = new HashMap<String, String>() ;
//
      private HashMap<String, String> fields;


     public GlossaryEntryClass1(String tag_gls2,String name_gls2, String symbol_gls2, String plural_gls2, String desc_gls2){
         fields = new HashMap<String, String>() ;


        //  fields.put(symbol_gls2, plural_gls2);
        if(tag_gls2 != null && !tag_gls2.isEmpty())
         fields.put("Tag", tag_gls2);
        
         if(name_gls2 != null && !name_gls2.isEmpty())
         fields.put("Name", name_gls2);
         
         if(symbol_gls2 != null && !symbol_gls2.isEmpty())
         fields.put("Symbol", symbol_gls2);
        
         if(plural_gls2 != null && !plural_gls2.isEmpty())
         fields.put("Plural", plural_gls2);
         
        if(desc_gls2 != null && !desc_gls2.isEmpty())
         fields.put("Description", desc_gls2);

        
        
         gMap.put(tag_gls2, fields) ;

    }

     String getTag(){
        return fields.get("Tag");
     }

     String getName(){
         return fields.get("Name");
     }

     String getSymbol(){
         return fields.get("Symbol");
     }

     String getPlural(){
         return fields.get("Plural");
     }

     String getDesc(){
         return fields.get("Description");
     }





     }






      //part of the gls dialog

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  System.setProperty("apple.laf.useScreenMenuBar", "true");
        // Start all Swing applications on the EDT.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GlossaryTool().setVisible(true);
//             PopupSubclass popupsbcls51 = new PopupSubclass();
//             popupsbcls51.createPopupMenu();

            }
        });
        // TODO code application logic here
    }


    // private class add_gls extends  TextAction {
    // for glossaries popup. #1 glossaries
    private class addGlossariecePopup extends TextAction {

        public addGlossariecePopup() {


           super("\\gls{ }");
        }

        public void actionPerformed(ActionEvent e) {
        String text_selected = textArea.getSelectedText();
         String text_replacement = "\\gls{" + text_selected + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;


        }

    }



  // for glossaries popup. #1 glossary
    public String addglsGlossariecePopup_Method(String tag){
 //String text_selected = textArea.getSelectedText();
         String text_replacement = "\\gls{" + tag + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
        return text_replacement ;
}


     // for glossaries popup. #2 glossaries plural glspl
      public String addglsplGlossariecePopup_Method(String tag){
   //String text_selected = textArea.getSelectedText();
          String text_replacement = "\\glspl{" + tag + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
         return text_replacement ;
}

      // for glossaries popup. #3 Glossaries Gls
         public String addGlosGlossariecePopup_Method(String tag){
       // String text_selected = textArea.getSelectedText();
      String text_replacement = "\\Gls{" + tag + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
         return text_replacement ;
}

      // for glossaries popup. #4 Glossary plural
           public String addGlosplGlossariecePopup_Method(String tag){
        //String text_selected = textArea.getSelectedText();
              String text_replacement = "\\Glspl{" + tag + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
         return text_replacement ;
}

      // for glossaries popup. #5 glossaries symbol
      public String addglssymbolGlossariecePopup_Method(String tag){
     //   String text_selected = textArea.getSelectedText();
       String text_replacement = "\\glssymbol{" + tag + "}" ;
        textArea.replaceSelection(text_replacement);
        textChanged = true;
         return text_replacement ;
}


      // for glossaries popup. #6 glossaries
    private class glossariseTheWordPopup extends TextAction {
 Intelligence intelligence = new Intelligence();
        public glossariseTheWordPopup() {


           super("Glossarise the word");
        }

        public void actionPerformed(ActionEvent e) {
            intelligence.glossariseTheWord();


        }

    }

       // for glossaries popup. #6 glossaries
    private class glossariseAllTheWordPopup extends TextAction {
 Intelligence intelligence = new Intelligence();
        public glossariseAllTheWordPopup() {


           super("Glossarise all the instances of this word");
        }

        public void actionPerformed(ActionEvent e) {
            intelligence.checkavailabilityin_Map4(textArea.getSelectedText());
//jhgjh

        }

    }
    
    
          // for highlighting single occurance 
    private class highlightSingleWordOccurance extends TextAction {
 Intelligence intelligence = new Intelligence();
        public highlightSingleWordOccurance() {


           super("Highlight all the instances of this word");
        }

        public void actionPerformed(ActionEvent e) {
            intelligence.checkavailabilityin_Map10();
//jhgjh

        }

    }
    
    
   

//    protected void appendGlossariecePopupMenu(JPopupMenu popup) {
//		popup.addSeparator();
//
//		//ResourceBundle bundle = ResourceBundle.getBundle(MSG);
//		//glossaryMenu = new JMenu(bundle.getString("ContextMenu.Folding"));
//		//glossaryMenu.add(createPopupMenuItem(toggleCurrentFoldAction));
//                glossaryMenu.add(glsToolItem);
//		glossaryMenu.add(GlsToolItem);
//		glossaryMenu.add(GlsToolItem);
//		glossaryMenu.add(GlsplToolItem);
//                glossaryMenu.add(glssymbolToolItem);
//		popup.add(glossaryMenu);
//
//	}

     private class glsToolItemPopup extends TextAction {

      public glsToolItemPopup() {
           super("glsToolItem");

        }

        public void actionPerformed(ActionEvent e) {

            	glossaryMenu.add("GlsToolItem");
		glossaryMenu.add("GlsToolItem");
		glossaryMenu.add("GlsplToolItem");
                glossaryMenu.add("glssymbolToolItem");
                createAndShowadd_glsDialog();

        }

    }



    // private class add_gls extends  TextAction {

    private class addtoGlossaryPopup extends TextAction {

        public addtoGlossaryPopup() {

//         Boolean checktagExists =readGFileObject.checkifSavedinGlossaryFile(textArea.getSelectedText());
//
//            if(!checktagExists)
//             {
//
//             }

           super("Add to Glossary");
        }

        public void actionPerformed(ActionEvent e) {
 String text_selected ; 
//            JTextComponent tc = getTextComponent(e);
//            String gls_word = null;
//
//            int selStart = tc.getSelectionStart();
//            int selEnd = tc.getSelectionEnd();
//            if (selStart != selEnd) {
//                try {
//                    gls_word = tc.getText(selStart, selEnd - selStart);
//                } catch (BadLocationException ex) {
//                    Logger.getLogger(GlossaryTool.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                add_to_glossary(gls_word);
//            } else {
//                // gls_word = getFilenameAtCaret(tc);
//                add_to_glossary(gls_word);
//            }
//
//            if (findDialog.isVisible()) {
//                findDialog.setVisible(false);
//            }
//            replaceDialog.setVisible(true);


      try {
            int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
            if (selStart != selEnd) {
               text_selected = textArea.getText(selStart, selEnd - selStart);
               
               if(!text_selected.equals(""))
               { createAndShowadd_glsDialog();
          }
            } 
          }catch (BadLocationException ble) {
            ble.printStackTrace();
     
         }


                

        }




        private void add_to_glossary(String word) {
//
        }

    }

    private class ShowFindDialogAction extends AbstractAction {

        public ShowFindDialogAction() {
            super("Find...");
            int c = getToolkit().getMenuShortcutKeyMask();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F, c));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (replaceDialog.isVisible()) {
                replaceDialog.setVisible(false);
            }
            findDialog.setVisible(true);
        }

    }

    private class ShowReplaceDialogAction extends AbstractAction {

        public ShowReplaceDialogAction() {
            super("Replace...");
            int c = getToolkit().getMenuShortcutKeyMask();
            putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_H, c));
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (findDialog.isVisible()) {
                findDialog.setVisible(false);
            }
            replaceDialog.setVisible(true);
        }

    }



    private static class StatusBar extends JPanel {

        private JLabel label;

        public StatusBar() {
            label = new JLabel("Ready");
            setLayout(new BorderLayout());
            add(label, BorderLayout.LINE_START);
            add(new JLabel(new SizeGripIcon()), BorderLayout.LINE_END);
        }

        public void setLabel(String label) {
            this.label.setText(label);
        }

    }


//another
   // http://www.java2s.com/Code/Java/Swing-JFC/JTextPaneHighlightExample.htm



  class UnderlineHighlighter extends DefaultHighlighter {
      // Shared painter used for default highlighting
  protected final HighlightPainter sharedPainter = new UnderlineHighlightPainter(null);

  // Painter used for this highlighter
  protected Highlighter.HighlightPainter painter;
    protected Color color; // The color for the underline

  public UnderlineHighlighter(Color c) {
    painter = (c == null ? sharedPainter : new UnderlineHighlightPainter(c));
  }

  // Convenience method to add a highlight with
  // the default painter.
  public Object addHighlight(int p0, int p1) throws BadLocationException {
    return addHighlight(p0, p1, painter);
  }

  public void setDrawsLayeredHighlights(boolean newValue) {
    // Illegal if false - we only support layered highlights
    if (newValue == false) {
      throw new IllegalArgumentException(
          "UnderlineHighlighter only draws layered highlights");
    }
    super.setDrawsLayeredHighlights(true);
  }

  //starts here class 5
//  // Painter for underlined highlights
//  public class UnderlineHighlightPainter extends LayeredHighlighter.LayerPainter {
//    public UnderlineHighlightPainter(Color c) {
//      color = c;
//    }
//
//    public void paint(Graphics g, int offs0, int offs1, Shape bounds,
//        JTextComponent c) {
//      // Do nothing: this method will never be called
//    }
//
//    public Shape paintLayer(Graphics g, int offs0, int offs1, Shape bounds,
//        JTextComponent c, View view) {
//      g.setColor(color == null ? c.getSelectionColor() : color);
//
//      Rectangle alloc = null;
//      if (offs0 == view.getStartOffset() && offs1 == view.getEndOffset()) {
//        if (bounds instanceof Rectangle) {
//          alloc = (Rectangle) bounds;
//        } else {
//          alloc = bounds.getBounds();
//        }
//      } else {
//        try {
//          Shape shape = view.modelToView(offs0,
//              Position.Bias.Forward, offs1,
//              Position.Bias.Backward, bounds);
//          alloc = (shape instanceof Rectangle) ? (Rectangle) shape
//              : shape.getBounds();
//        } catch (BadLocationException e) {
//          return null;
//        }
//      }
//
//      FontMetrics fm = c.getFontMetrics(c.getFont());
//      int baseline = alloc.y + alloc.height - fm.getDescent() + 1;
//      g.drawLine(alloc.x, baseline, alloc.x + alloc.width, baseline);
//      g.drawLine(alloc.x, baseline + 1, alloc.x + alloc.width,
//          baseline + 1);
//
//      return alloc;
//    }
//
//
//  }

  /// ends here 5
  }

  //glossary replace all
//  public final class ReplaceSubstring {
//
//  /**
//  *  Simplest in Java 1.5, using the replace method, which
//  *  takes CharSequence objects.
//  */
//  public String replace15(String aInput, String  aOldPattern, String aNewPattern){
//    return aInput.replace(aOldPattern, aNewPattern);
//  }
//
//
//  /** Example: update an ip address appearing in a link.  */
//  public  void main (String[] aArguments) {
//    String OLD_IP = "45.23.102.12";
//    //escape the  '.', a special character in regular expressions
//    String OLD_IP_REGEX = "45\\.23\\.102\\.12";
//    String NEW_IP = "99.104.106.95";
//    String LINK = "http://45.23.102.12:8080/index.html";
//
//    //log("Old link : " + LINK);
//
//    String newLink = replace15(LINK, OLD_IP, NEW_IP);
//  //  log("New link with Java 1.5 replace: " + newLink);
//
//  }
//
////  private void log(String aMessage){
////    System.out.println(aMessage);
////  }
//}

// delete from
// public int search(String word) {
//    int firstOffset = -1;
//   JTextComponent comp = textArea ;
//
//
////    if(repeatTags.containsKey(text_selected)==true)
//////        textArea.setText(textArea.getText().replaceAll("(\\b"+text_selected+"\\b)", repeatTags.get(text_selected)));
////               textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+text_selected+"(?!\\S)", repeatTags.get(text_selected)));
//
//
//    if (word == null || word.equals("")) {
//      return -1;
//    }
//
//    // Look for the word we are given - insensitive search
//    String content = null;
//    try {
//      Document d = comp.getDocument();
//      content = d.getText(0, d.getLength()).toLowerCase();
//    } catch (BadLocationException e) {
//      // Cannot happen
//      return -1;
//    }
//
//    word = word.toLowerCase();
//   String word1 = "(?<!\\S)"+word+"(?!\\S)" ;
//    int lastIndex = 0;
//    int wordSize = word.length();
//
//    while ((lastIndex = content.indexOf(word, lastIndex)) != -1) {
//
//int endIndex = lastIndex + wordSize;
// Boolean available = checkavailabilityin_Map1();
//                int offset = searcher.search(word);
//      if (firstOffset == -1) {
//        firstOffset = lastIndex;
//      }
//      lastIndex = endIndex;
//    }
//
//    return firstOffset;
//  }
  //delete here


  //Intelligence *starts here*
  public class Intelligence {

      private void glossariseTheWord(){
        String text_replacement ;
        String tag = null ;

        //Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
        String text_selected = textArea.getSelectedText();

     

         
          try {
            int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
            if (selStart != selEnd) {
               text_selected = textArea.getText(selStart, selEnd - selStart);
               
               if(!text_selected.equals(""))
               checkavailabilityin_Map(text_selected);
            } 
          }catch (BadLocationException ble) {
            ble.printStackTrace();
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            return;
         }
          
             boolean blnExists = repeatTags.containsKey(text_selected);
         //tag = checkifPlural(text_selected) ;
         

//        if(blnExists)
//        { // System.out.println("exists") ;
//            String repeatTags_value = repeatTags.get(text_selected);
//       textArea.replaceSelection(repeatTags_value);
//       System.out.println("found in tags"+repeatTags);
//
//        }

        //textArea.replaceSelection(text_replacement);
        textChanged = true;




      }

   public String replaceOld(String aInput,String text_selected,final String aNewPattern)
   {  //text_selected = textArea.getSelectedText();
      //aInput = textArea.getText();
     if ( text_selected.equals("") ) {
        throw new IllegalArgumentException("Old pattern must have content.");
     }

     final StringBuffer result = new StringBuffer();
     //startIdx and idxOld delimit various chunks of aInput; these
     //chunks always end where aOldPattern begins
     int startIdx = 0;
     int idxOld = 0;
     while ((idxOld = aInput.indexOf(text_selected, startIdx)) >= 0) {
       //grab a part of aInput which does not include aOldPattern
       result.append( aInput.substring(startIdx, idxOld) );
       //add aNewPattern to take place of aOldPattern
       result.append( aNewPattern );

       //reset the startIdx to just after the current match, to see
       //if there are any further matches
       startIdx = idxOld + text_selected.length();
     }
     //the final chunk will go to the end of aInput
     result.append( aInput.substring(startIdx) );
     return result.toString();
  }



   public String replaceAllglossary(String aInput, String  aOldPattern, String aNewPattern){
    return aInput.replace(aOldPattern, aNewPattern);
      }

   

   private void glossariseAllTheWord(){
       
        String tag = null ;

        //Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
        String text_selected = textArea.getSelectedText();


          
             try {
            int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
            if (selStart != selEnd) {
               text_selected = textArea.getText(selStart, selEnd - selStart);
               
               if(!text_selected.equals(""))
              checkavailabilityin_Map4(text_selected);
            } 
          }catch (BadLocationException ble) {
            ble.printStackTrace();
            UIManager.getLookAndFeel().provideErrorFeedback(textArea);
            return;
         }
          

        textChanged = true;




      }

      public Boolean capitalornot (String text_selected)
      { Boolean capitalcheck ;
          if(Character.isUpperCase(text_selected.charAt(0))==true)
                capitalcheck = true ;
          else
            capitalcheck = false ;

          return capitalcheck ;
      }


      public String checkavailabilityin_Map(String text_selected){
          String tag = null ;
          String text_replacement = null ;
          Boolean capitalcheck= capitalornot(text_selected);
          Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();

            if(text_selected.toLowerCase().equals(entry.getValue().get("Tag")))
            { tag = (String) entry.getValue().get("Tag");
                if(capitalcheck)
                {text_replacement=addGlosGlossariecePopup_Method(tag);
                repeattags_add(text_selected,text_replacement);
                 }
                 else
                { text_replacement=addglsGlossariecePopup_Method(tag);
                repeattags_add(text_selected,text_replacement);
                }

                break ; }

            else if(text_selected.toLowerCase().equals(entry.getValue().get("Plural")))
            { tag = (String) entry.getValue().get("Tag");
                if(capitalcheck)
                { text_replacement=addGlosplGlossariecePopup_Method(tag);
                repeattags_add(text_selected,text_replacement); }

                else
            { text_replacement=addglsplGlossariecePopup_Method(tag);
             repeattags_add(text_selected,text_replacement); }
                break ;}

            else if(text_selected.equals(entry.getValue().get("Symbol")))
            { tag = (String) entry.getValue().get("Tag");
                text_replacement=addglssymbolGlossariecePopup_Method(tag);
                    repeattags_add(text_selected,text_replacement);
                break ; }

                //  iterator.remove(); // right way to remove entries from Map, // avoids ConcurrentModificationException
        }

           return text_replacement ;
      }

       // checks and highlights every instance of all words  
      public String checkavailabilityin_Map1(){

          String value=null ;
           remove_highlight.removehighlighter();

          Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();

            value = (String) entry.getValue().get("Tag") ;
            searcher.search(value);

            value = (String) entry.getValue().get("Plural") ;
            searcher.search(value);
          value = (String)  entry.getValue().get("Symbol") ;
            searcher.search(value);


        }

           return value ;
      }
       
     // replace all for instance of all the words
     public String checkavailabilityin_Map3(){
          String tag = null ;
          String text_replacement = null ;
          String value ;
                String capValue ;
                  String lowerValue ;
        
          Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();
              
            tag = (String) entry.getValue().get("Tag");
          
         

            value = (String)  entry.getValue().get("Tag") ;
             
            capValue = value.substring(0, 1).toUpperCase() + value.substring(1);
          //  text_replacement=addGlosGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\\\Gls{" + tag + "}")); 
             
            lowerValue = value.substring(0, 1).toLowerCase() + value.substring(1);
           //  text_replacement=addglsGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\\\gls{" + tag + "}")); 
            
           
            
            value = (String)  entry.getValue().get("Plural") ;
            
            capValue = value.substring(0, 1).toUpperCase() + value.substring(1);
           // text_replacement=addGlosplGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\\\Glspl{" + tag + "}")); 
             
            lowerValue = value.substring(0, 1).toLowerCase() + value.substring(1);
           // text_replacement=addglsplGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\\\glspl{" + tag + "}")); 
            
            
            value = (String)  entry.getValue().get("Symbol") ;
            //text_replacement=addglssymbolGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+value+"(?!\\S)", "\\\\glssymbol{" + tag + "}"));

          
        }

           return text_replacement ;
      } 
     
     
         // checks and highlights  instance of a single word
      public String checkavailabilityin_Map10(){
            Boolean check ;
          String value=null ;
           String text_selected ;
          
          try {
            int selStart = textArea.getSelectionStart();
            int selEnd = textArea.getSelectionEnd();
            if (selStart != selEnd) {
               text_selected = textArea.getText(selStart, selEnd - selStart);
               
               if(!text_selected.equals(""))
               {
               Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();
            check = false ;
              if(text_selected.toLowerCase().equals(entry.getValue().get("Tag")))
                 check = true ;
             else if(text_selected.toLowerCase().equals(entry.getValue().get("Plural")))
                 check = true ;
             else if(text_selected.toLowerCase().equals(entry.getValue().get("Symbol")))
                   check = true ;

           if(check)
           {value = (String) entry.getValue().get("Tag") ;
            searcher.search(value);

            value = (String) entry.getValue().get("Plural") ;
            searcher.search(value);
          value = (String)  entry.getValue().get("Symbol") ;
            searcher.search(value);
           }

        }}
            } 
          }catch (BadLocationException ble) {
            ble.printStackTrace();
     
         }
          

       

           return value ;
      }
     
     // glossarise only the current word
     public String checkavailabilityin_Map4(String text_selected){
          String tag = null ;
          String plural = null ;
                String capValue ;
              String lowerValue ; 
               Boolean check  ;
          String symbol = null ;
          String text_replacement = null ;
          Boolean capitalcheck= capitalornot(text_selected);
          Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();
            tag = (String) entry.getValue().get("Tag");
           check = false ;
            
             if(text_selected.toLowerCase().equals(entry.getValue().get("Tag")))
                 check = true ;
             else if(text_selected.toLowerCase().equals(entry.getValue().get("Plural")))
                 check = true ;
             else if(text_selected.toLowerCase().equals(entry.getValue().get("Symbol")))
                   check = true ;
             //else check = false ;
            
            
             if(check) {
                  tag = (String) entry.getValue().get("Tag");
                   plural = (String) entry.getValue().get("Plural");
                    symbol = (String) entry.getValue().get("Symbol");

            capValue = text_selected.substring(0, 1).toUpperCase() + text_selected.substring(1);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\\\Gls{" + tag + "}")); 
             
              
            lowerValue = text_selected.substring(0, 1).toLowerCase() + text_selected.substring(1);
        
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\\\gls{" + tag + "}")); 
    
            // plural = (String) entry.getValue().get("Plural");
             
            capValue = plural.substring(0, 1).toUpperCase() + plural.substring(1);
        
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\\\Glspl{" + tag + "}")); 
          
            lowerValue = plural.substring(0, 1).toLowerCase() + plural.substring(1);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\\\glspl{" + tag + "}"));  
        
    
             
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+symbol+"(?!\\S)", "\\\\glssymbol{" + tag + "}"));
      
             
        }}

           return text_replacement ;
      }
      
 public void glossarycheckconditions (String str){
              String capValue ;
              String lowerValue ;  
              String value ;
              String tag =null;
              
              Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();
                
            tag = (String) entry.getValue().get("Tag");
          
 
            value = (String)  entry.getValue().get("Tag") ;
             
            capValue = value.substring(0, 1).toUpperCase() + value.substring(1);
          //  text_replacement=addGlosGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\Gls{" + tag + "}")); 
             
            lowerValue = value.substring(0, 1).toLowerCase() + value.substring(1);
           //  text_replacement=addglsGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\gls{" + tag + "}")); 
            
           
             value = (String)  entry.getValue().get("Plural") ;
            
            capValue = value.substring(0, 1).toUpperCase() + value.substring(1);
           // text_replacement=addGlosplGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+capValue+"(?!\\S)", "\\Glspl{" + tag + "}")); 
             
            lowerValue = value.substring(0, 1).toLowerCase() + value.substring(1);
           // text_replacement=addglsplGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+lowerValue+"(?!\\S)", "\\glspl{" + tag + "}")); 
            
           
            value = (String)  entry.getValue().get("Symbol") ;
            //text_replacement=addglssymbolGlossariecePopup_Method(tag);
            textArea.setText(textArea.getText().replaceAll( "(?<!\\S)"+value+"(?!\\S)", "\\glssymbol{" + tag + "}"));
            
           }
 }
 public void repeattags_add(String selected_word2, String replacement_word)
  {
      repeatTags.put(selected_word2,replacement_word);

  }


  }

// Intelligence *ends here*
  //reference : http://stackoverflow.com/questions/37462264/recursively-flatten-values-of-nested-maps-in-java-8





  //flatten the gMap map  * starts here*
  public static Stream<Object> flatten(Object o) {
        if (o instanceof Map<?, ?>) {
            return ((Map<?, ?>) o).values().stream().flatMap(GlossaryTool::flatten);
        }
        return Stream.of(o);
    }

//    public GlossaryTool(Map<String, Map> gMap1) {
//        collect = gMap1.values().stream().flatMap(GlossaryTool::flatten)
//                .collect(Collectors.toList());
////           List<Object> collect = gMap1.values().stream().flatMap(GlossaryTool::flatten).collect(Collectors.toList());
//
//
//        storemap(collect);
//        System.out.println(collect);
//        // or
////        List<Object> collect2 = flatten(map0).collect(Collectors.toList());
////        System.out.println(collect);
//
//    }

     public static List storemap(List<Object> collect1)
     {

             return collect1 ;
     }

      public List retrievelist()
     {
             return collect ;
     }

        //flatten the gMap map *ends here*


 
      
        public void writeto_GlossaryFile(){
        
          String all_gls  ;
          String value ;
    
          Iterator<Map.Entry<String, Map>> iterator = gMap.entrySet().iterator();
           while(iterator.hasNext()){
            Map.Entry<String, Map> entry = iterator.next();

             all_gls = null ;
       
            value = (String)  entry.getValue().get("Tag") ; 
            // if(!value.equals(""))
             all_gls = "\n\\newglossaryentry{" + value + "}\n" + "{" ;
            
             value = (String)  entry.getValue().get("Name") ; 
             if(value != null && !value.isEmpty())
            { all_gls=  all_gls + "\n\tname={" + value + "}," ;  }
             
             
              value = (String)  entry.getValue().get("Plural") ; 
            if(value != null && !value.isEmpty())
            {  all_gls=  all_gls + "\n\tplural={" + value + "}," ; }
              
            
               value = (String)  entry.getValue().get("Symbol") ;
             if(value != null && !value.isEmpty())
               { all_gls=  all_gls + "\n\tsymbol={" + value + "}," ; }
              
             
              
              
              value = (String)  entry.getValue().get("Description") ;
                //if(!"".equals(value))
                if(value != null && !value.isEmpty())
                { all_gls=  all_gls + "\n\tdescription={" + value + "}" ;}
                      
                  all_gls= all_gls+ "\n}" ;
                     
            System.out.println("after desc"+ all_gls) ;
                      
          try {
         FileWriter out_gls;
           // out = new FileWriter(fn);

            out_gls = new FileWriter(glossaryFileName,true);
            out_gls.write(all_gls);
            out_gls.close();

        } catch (Exception err) {
            System.out.println("Error in saving to glossary: " + err);
        }     
                  
           
          

              
        }

      
      }   
      
      
      
}

