package circles;


import java.io.*;
import java.net.URL;
import java.util.*;


public class FriendCircles
{
    // Don't bother with an enum here
    private static final char NOT_FRIEND = 'N';


    // Probably overkill to write a class but it helps me see the problem cleaner
    // when using "natural language
    static class Person
    {
        private int id;
        private Set< Person > friends = new HashSet<>( );

        Person ( int _id)
        {
            id = _id;
        }

        void addFriend(Person friend)
        {
            friends.add( friend );
        }


        public int hashCode ( )
        {
            return id;
        }


        public boolean equals ( Object _other )
        {
            if (_other == null)
            {
                return false;
            }

            if ( !( _other instanceof Person ) )
            {
                return false;
            }

            return hashCode() == _other.hashCode();
        }


        public Set<Person> getFriends()
        {
            return friends;
        }


        public String toString()
        {
            return String.valueOf( id );
        }
    }


    // Main entry point
    private static int friendCircles(String[] friends)
    {
        // First pre-process
        Person[] people = preprocessFriends( friends);

        // Then for each friend, do a depth search to find reachable friends
        List< Set< Person > > uniqueSets = new LinkedList<>( );

        for (Person person: people)
        {
            Set< Person > circle = searchFriends( person );

            // See if we've found the set before
            boolean found = false;
            for (Set<Person> foundSet : uniqueSets)
            {
                if (circle.containsAll( foundSet ))
                {
                    found = true;
                    break;
                }
            }

            if (!found)
            {
                uniqueSets.add( circle );
            }
        }


        return uniqueSets.size();
    }


    private static Set<Person> searchFriends ( Person person )
    {
        Set<Person> visited = new HashSet<>( );
        visited.add( person );

        Set<Person> circle = new HashSet<>( );
        circle.addAll( person.getFriends() );

        // traverse the friend relationships until the visited sets and the circle sets
        // are the same
        while ( !visited.containsAll( circle ) )
        {
            Set< Person > newFriends = new HashSet<>( );
            for (Person friend: circle)
            {
                // If we visited the friend, no need to visit again
                if (visited.contains( friend ))
                {
                    continue;
                }

                // Now we visit
                visited.add( friend );
                // Add all this friends connections to the circle
                newFriends.addAll( friend.getFriends( ) );
            }

            circle.addAll( newFriends );
        }

        return circle;
    }


    /**
     * Pre-process the matrix of friends
     *
     * @param friends
     * @return
     */
    private static Person[] preprocessFriends( String[] friends )
    {
        Person[] person = new Person[ friends.length ];

        for ( int i = 0 ; i < person.length ; i++ )
        {
            person[ i ] = new Person( i );
        }

        for (int i = 0; i < friends.length; i++)
        {
            Person current = person[ i ];

            String connections = friends[ i ];
            for (int j = 0; j < connections.length(); j++)
            {
                char friendStatus = connections.charAt( j );
                if (friendStatus == NOT_FRIEND)
                {
                    // I like to continue early
                    continue;
                }

                Person friend = person[ j ];
                current.addFriend( friend );
                friend.addFriend( current );
            }
        }

        return person;
    }



    public static void oldMain(String[] args)
    throws
    IOException
    {
        Scanner in = new Scanner( System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter( new FileWriter( fileName) );
        int res;

        int _friends_size = Integer.parseInt(in.nextLine());
        String[] _friends = new String[_friends_size];
        String _friends_item;
        for(int _friends_i = 0; _friends_i < _friends_size; _friends_i++) {
            try {
                _friends_item = in.nextLine();
            } catch (Exception e) {
                _friends_item = null;
            }
            _friends[_friends_i] = _friends_item;
        }

        res = friendCircles( _friends );

        bw.write(String.valueOf(res));
        bw.newLine();

        bw.close();

    }


    private static String[] readFromFile()
    {
        try
        {
            URL resource = FriendCircles.class.getResource( "friends2.txt" );
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




    public static void main(String[] args) throws
                                           IOException
    {
        String[] strings = readFromFile( );
        friendCircles( strings );
    }

}
