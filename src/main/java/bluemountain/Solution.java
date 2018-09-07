package bluemountain;


import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Solution
{

    class TreeNode
    {
        private String name;
        private TreeNode left;
        private TreeNode right;

//        TreeNode(TreeNode _left, TreeNode _right)
        TreeNode(String name)
        {
            this.name = name;
//            this.left = _left;
//            this.right = _right;
        }

        public String getName()
        {
            return name;
        }

        public void setLeft(TreeNode _left)
        {
            this.left = _left;
        }


        public void setRight(TreeNode _right)
        {
            this.right = _right;
        }

        public TreeNode getLeft()
        {
            return left;
        }

        public TreeNode getRight()
        {
            return right;
        }

        public boolean hasNoChildren()
        {
            return left == null && right == null;
        }

        public boolean hasChildren()
        {
            return left != null || right != null;
        }

        private String leftName()
        {
            return left != null ? left.getName() : "?";
        }

        private String rightName()
        {
            return right != null ? right.getName() : "?";
        }

        @Override
        public String toString()
        {

            return "Node(" + name + "[" + leftName() + ", " + rightName() +"])";
        }
    }


    private static InputStream readFromFile()
    {
        try
        {
            URL resource = Solution.class.getResource( "bluemountain1" );
            File file = new File( resource.getFile( ) );
            return new FileInputStream( file );
        }
        catch ( IOException e )
        {
            return null;
        }
    }


    private TreeNode constructIfNotFound(String name, Map<String, TreeNode> nodeMap)
    {
        if (name == null || name.length() <= 0)
        {
            return null;
        }

        if (!nodeMap.containsKey( name ))
        {
            TreeNode node = new TreeNode( name );
            nodeMap.put( name, node );
        }

        return nodeMap.get( name );
    }


    private TreeNode constructNode(String line, Map<String, TreeNode> nodeMap)
    {
        if (line == null || line.length() == 0)
        {
            return null;
        }

        String[] nodeNames = line.split( "," );

        TreeNode parent = constructIfNotFound( nodeNames[ 0 ], nodeMap );
        TreeNode left = nodeNames.length > 1 ? constructIfNotFound( nodeNames[ 1 ], nodeMap ) : null;
        TreeNode right = nodeNames.length > 2 ? constructIfNotFound( nodeNames[ 2 ], nodeMap ) : null;

        parent.setLeft( left );
        parent.setRight( right );

        return parent;
    }


    public TreeNode readTree(InputStream stream)
    {
        Scanner in = stream != null ? new Scanner( stream ) : new Scanner( readFromFile( ) );

        if (!in.hasNext())
        {
            return null;
        }

        Map<String, TreeNode> treeNodeMap = new HashMap<>();
        String rootLine = in.next();

        TreeNode root = constructNode(rootLine, treeNodeMap);
//        System.out.println( root );

        while (in.hasNext()) {
            String nodeLine = in.nextLine( );
            TreeNode node = constructNode( nodeLine, treeNodeMap );
//            System.out.println( node );
        }


        return root;
    }


    public int findShallowest(TreeNode root)
    {
        if (root == null)
        {
            return 0;
        }

        int depth = 0;

        List<TreeNode> nodesAtLevel = new LinkedList<>( );
        nodesAtLevel.add( root );

        boolean barren = anyNodeBarren( nodesAtLevel );
        if (barren) {
            return depth;
        }

        return checkChildren( depth + 1, nodesAtLevel );
    }


    private int checkChildren(int depth, List<TreeNode> nodes)
    {
        List< TreeNode > children = gatherChildren( nodes );
//        printNodes( depth, children );
        if (anyNodeBarren( children ))
        {
            return depth;
        }

        return checkChildren( depth + 1, children );
    }

    public boolean anyNodeBarren( List<TreeNode> nodes )
    {
        return nodes.stream( )
                    .anyMatch( TreeNode::hasNoChildren );
    }


    public List<TreeNode> gatherChildren( List<TreeNode> nodes)
    {
        return nodes.stream( )
                    .flatMap( node ->
                              {
                                  return Stream.concat( Stream.of( node.getLeft( ) ),
                                                        Stream.of( node.getRight( ) ) )
                                               .filter( Objects::nonNull );
                              } )
                    .collect( Collectors.toList( ) );
    }

    public void printNodes( int level, List<TreeNode> nodes)
    {
        System.out.println( "Nodes " + level );
        nodes.forEach( System.out::print );
        System.out.println();
    }


    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        Solution sol = new Solution();
        TreeNode root = sol.readTree(null);
        System.out.println( sol.findShallowest( root ) );
    }
}