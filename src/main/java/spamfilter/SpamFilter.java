package spamfilter;


import java.util.*;


/**
 * Created by andrew on 12/30/16.
 */
public class SpamFilter
{
    boolean failMessageSize(String[] splits)
    {
        return (splits.length < 5);
    }

    boolean failSignals(Set<String> signals, String[] words, Set<String> found)
    {
        for (String word: words)
        {
            if (signals.contains(word))
            {
                found.add(word);
                return true;
            }
        }

        return false;
    }

    void captureContent(HashMap<String, Integer> map, String content)
    {
        Integer count = map.get(content);
        if (count != null)
        {
            map.put(content, ++count);
        }
        else
        {
            map.put(content, 1);
        }
    }


    void captureUserMessageCount(HashMap<String, Integer> map, String content)
    {
        Integer count = map.get(content);
        if (count != null)
        {
            map.put(content, ++count);
        }
        else
        {
            map.put(content, 1);
        }
    }


    void captureUser(HashMap<String, HashMap<String, Integer>> map, String content, String user)
    {
        HashMap<String, Integer> userMap = map.computeIfAbsent(content, (key) -> new HashMap<>());
        captureContent(userMap, user);
    }


    int greatestCommonDivisor(int first, int second)
    {
        if (first == 0 || second == 0)
        {
            return first + second;
        }

        return (greatestCommonDivisor(second, first%second));
    }

    boolean formatSize(String[] result, int messageCount, int lessThan5Count)
    {
        if ((double)lessThan5Count / messageCount > .90)
        {
            int gcd = greatestCommonDivisor(lessThan5Count, messageCount);

            if (gcd == messageCount && lessThan5Count < messageCount)
            {
                result[0] = String.format("failed: %d/%d", lessThan5Count, messageCount);
            }
            else
            {
                result[0] = String.format("failed: %d/%d", lessThan5Count/gcd, messageCount/gcd);
            }
            return true;
        }
        else
        {
            result[0] = "passed";
        }

        return false;
    }


    String formatSignal(int messageCount, int signalCount, Set<String> signals)
    {

        if ((double)signalCount / messageCount > .5)
        {
            String formatted = String.join(" ", signals);
            return String.format("failed: %s", formatted);
        }
        else
        {
            return "passed";
        }
    }


    boolean formatOverallContent(String[] result, HashMap<String, Integer> map, int total)
    {
        for (Map.Entry<String, Integer> entry: map.entrySet())
        {
            String message = entry.getKey();
            Integer count = entry.getValue();

            if ((double)count/total > 0.5 && total > 1)
            {
                result[2] = "failed: " + message;
                return true;
            }
        }

        result[2] = "passed";
        return false;
    }

    boolean formatDuplicateContent(String result[], HashMap<String, Integer> userCount, HashMap<String, HashMap<String, Integer>> messageCount)

    {
        TreeSet<String> spamUsers = new TreeSet<String>();

        for (Map<String, Integer> userMap: messageCount.values())
        {
            for (Map.Entry<String, Integer> entry: userMap.entrySet())
            {
                String user = entry.getKey();
                int totalCount = userCount.get(user);
                int spam = entry.getValue();

                if ((double)spam/totalCount > .5 && totalCount >= 2)
                {
                    spamUsers.add(user);
                }
            }
        }


        if (spamUsers.size() >= 1)
        {
            String formatted = String.join(" ", spamUsers);
            result[1] = String.format("failed: %s", formatted);
            return true;
        }

        result[1] = "passed";
        return false;
    }


    String[] spamDetection(String[][] messages, String[] spamSignals) {
        String[] result = new String[4];
        int lessThan5Count = 0;
        int signalCount = 0;

        TreeSet<String> signals = new TreeSet<String>();
        HashMap<String, Integer> content = new HashMap<String, Integer>();
        HashMap<String, Integer> userCount = new HashMap<String, Integer>();


        HashMap<String, HashMap<String, Integer>> messageCount = new HashMap<String, HashMap<String, Integer> >();

        Set<String> spamSet = new HashSet<String>();
        for (String signal: spamSignals)
        {
            spamSet.add(signal.toLowerCase());
        }

        Map<String, Set<String> > messageHash = new HashMap<String, Set<String>>();

        for (int i=0; i < messages.length; i++)
        {
            String[] messageInfo=messages[i];
            String message = messageInfo[0];
            String user = messageInfo[1];

            captureContent(content, message);
            captureUserMessageCount( userCount, user);
            captureUser(messageCount, message, user);

            String[] split = message.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
            if(failMessageSize(split))
            {
                ++lessThan5Count;
            }

            if (failSignals(spamSet, split, signals))
            {
                ++signalCount;
            }

        }

        boolean anyFailed = false;

        boolean sizeFailed = formatSize(result, messages.length, lessThan5Count);
        boolean duplicate = formatDuplicateContent(result, userCount, messageCount);
        boolean overall = formatOverallContent(result, content, messages.length);


        result[3] = formatSignal(messages.length, signalCount, signals);

        return result;
    }


}
