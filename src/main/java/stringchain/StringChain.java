package stringchain;


import java.io.*;
import java.net.URL;
import java.util.*;


/**
 * Created by andrew on 2/7/16.
 */
public class StringChain
{
    private static Set<Character> wordAsSet(String word)
    {
        Set< Character > charSet = new HashSet<>( );
        for (Character c: word.toCharArray())
        {
            charSet.add( c );
        }

        return charSet;
    }


    // pre process a length to word map
    private static Map< Integer, Set< String > > prepareMap( String[] words )
    {
        Map< Integer, Set< String > > sizesToStrings = new TreeMap<>( );

        for(String current: words)
        {
            int sizeOfString = current.length();

            Set< String > stringsOfCurrentSize = sizesToStrings.get( sizeOfString );
            if (stringsOfCurrentSize == null)
            {
                stringsOfCurrentSize = new HashSet<>( );
                sizesToStrings.put( sizeOfString, stringsOfCurrentSize );
            }

            //Sort the word to reduce search space since there may be anagrams
            char[] c = current.toCharArray();
            java.util.Arrays.sort(c);
            current = new String(c);

            stringsOfCurrentSize.add( current );
        }

        return sizesToStrings;
    }


    /*
    This will find all the paths recursively.  There is definitely a lot of room for improvement here and can
    use some dynamic programming to keep track of prior paths.  Furthermore, I'm expanding and keeping
    all paths which is not a good idea.

     */
    private static List< Set< String > > traverseMap(Map<Integer, Set<String>> map)
    {
        List< Set< String > > paths = new LinkedList<>( );

        Set< String > visited = new HashSet<>( );

        for (int length = 50; length > 0; length--)
        {
            Set< String > values = map.get( length );
            if (values == null)
            {
                continue;
            }

            boolean reachedBottom = false;
            for (String item: values)
            {
                Set<String> path = new HashSet<>( );
                path.add( item );
                reachedBottom = traverse( length, visited, paths, path, item, map );

                if (reachedBottom)
                {
                    break;
                }
            }

            if ( reachedBottom )
            {
                break;
            }
        }

        return paths;
    }


    /*
    Traverse the paths.. Keep track of the current word length.  If we reach the bottom and the word
    length matches then we stop looking
     */
    private static boolean traverse ( int length,
                                      Set< String > visited,
                                      List< Set< String > > paths,
                                      Set< String > path,
                                      String item,
                                      Map< Integer, Set< String > > map )
    {
        if (visited.contains( item ))
        {
            return false;
        }

        paths.add( path );

        if (path.size() == length)
        {
            return true;
        }

        visited.add( item );

        int lengthToLookFor = item.length() - 1;

        Set< String > strings = map.get( lengthToLookFor );
        if (strings == null)
        {
            return false;
        }


        Set< Character > itemLetters = wordAsSet( item );

        for (String potentialParent: strings)
        {
            if ( visited.contains( potentialParent ) )
            {
                continue;
            }

            Set< Character > potentialLetters = wordAsSet( potentialParent );
            if (itemLetters.containsAll( potentialLetters ) )
            {
                Set<String> newPath = new HashSet<>( path );
                newPath.add( potentialParent );
                if (traverse( length, visited, paths, newPath, potentialParent, map ))
                {
                    return true;
                }
            }
        }

        return false;
    }


    private static int computeMax(List< Set< String > > paths)
    {
        int maxLength = 0;

        for (Set<String> path: paths )
        {
            System.out.println( path );
            if (path.size() > maxLength)
            {
                maxLength = path.size();
            }
        }

        return maxLength;
    }


    private static int longest_chain(String[] w)
    {
        Map< Integer, Set< String > > integerSetMap = prepareMap( w );

        return computeMax( traverseMap( integerSetMap ) );
    }


    private static String[] readFromFile()
    {
        try
        {
            URL resource = StringChain.class.getResource( "chain.txt" );
            File file = new File( resource.getFile( ) );

            FileReader fileReader = new FileReader( file );
            BufferedReader bufferedReader = new BufferedReader( fileReader );

            LinkedList< String > lines = new LinkedList<>( );

            String s = bufferedReader.readLine( );

            while ( s != null )
            {
                lines.add( s );
                s = bufferedReader.readLine( );
            }

            return lines.toArray( new String[lines.size()] );
        }
        catch ( IOException e )
        {
            return null;
        }

    };


    public static void main(String[] args)
    throws
    IOException
    {
        String[] strings = readFromFile( );


        System.out.println( longest_chain( strings ) );
    }



    public static void oldmain(String[] args) throws IOException{
        Scanner in = new Scanner( System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter( fileName));
        int res;

        int _w_size = 0;
        _w_size = Integer.parseInt(in.nextLine());
        String[] _w = new String[_w_size];
        String _w_item;
        for(int _w_i = 0; _w_i < _w_size; _w_i++) {
            try {
                _w_item = in.nextLine();
            } catch (Exception e) {
                _w_item = null;
            }
            _w[_w_i] = _w_item;
        }

        res = longest_chain(_w);
        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();
    }

}
