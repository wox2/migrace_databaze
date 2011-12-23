/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package NotImplemented;

/**
 *
 * @author woxie
 */
public class GraphSaver {
    public GraphSaver(String fileName){
        
    }

    public boolean save(){


        return false;
    }

}

//        {
//
//            try {
//                Graph g = null;
//                GraphGenerator graphGenerator = new GraphGenerator();
//                g = graphGenerator.generateGraph(12, 32);
//                //g.printEdges();
//
//                String filename = "t.tmp";
//                FileOutputStream fos = null;
//                fos = new FileOutputStream(filename);
//                ObjectOutputStream oos = new ObjectOutputStream(fos);
//                oos.writeObject(g);
//
//                fos.close();
//
//            } catch (FileNotFoundException ex) {
//                System.out.println("Nenasel jsem file");
//            } catch (GraphGeneratingException ex) {
//                System.out.println("Generator nenageneroval");
//            } catch (IOException ex) {
//                System.out.println("Vyskytla se IOException");
//            }
//
//            {
//                FileInputStream fis = null;
//                try {
//                    String filename = "t.tmp";
//                    fis = new FileInputStream(filename);
//                    ObjectInputStream ois = new ObjectInputStream(fis);
//                    Graph g = (Graph)ois.readObject();
//                    fis.close();
//                    ois.close();
//                    g.printEdges();
//
//                } catch (FileNotFoundException ex) {
//                    System.out.println("File not found");
//                } catch (ClassNotFoundException ex) {
//                    System.out.println("Trida nebyla nalezena");
//                }
//                 catch (IOException ex) {
//                    System.out.println("Vyjimka");
//                }
//
//            }
//        }
//
//
