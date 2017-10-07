package careercup.gaps;

import java.util.Iterator;

public class Gaps
{
  private static class GapIterator implements Iterator<Integer>
  {
    private final int[] initial;
    private int index = 0;
    private int start = 1;

    GapIterator(int[] initial)
    {
      this.initial = initial;
    }


    @Override
    public boolean hasNext()
    {
      if (start < initial[index]) {
        return true;
      }

      do
      {
        ++index;
        while (indexWithinBounds() && ++start < initial[index])
        {
          return true;
        }
      }
      while (indexWithinBounds());

      return false;
    }


    private boolean indexWithinBounds()
    {
      return index < initial.length;
    }


    @Override
    public Integer next()
    {
      return start++;
    }
  }


  private final int[] initial;

  Gaps(int[] initial)
  {
    this.initial = initial;
  }

  int[] getGaps()
  {
    int maxIndex = initial.length - 1;
    int maxNum = initial[maxIndex];
    int[] gaps = new int[maxNum - initial.length];

    int initIndex = 0;
    int gapIndex = 0;

    int s = 1;

    while(initIndex < maxIndex)
    {
      if (s < initial[initIndex])
      {
        gaps[gapIndex++] = s++;
      }
      else
      {
        while (initIndex < maxIndex && ++s < initial[initIndex++]);
      }
    }

    if (gaps[gapIndex] == 0)
    {
      gaps[gapIndex] = s;
    }

    return gaps;
  }


  GapIterator gapIterator()
  {
    return new GapIterator(this.initial);
  }

  public static void main(String[] args)
  {
    int[] initialSet1 = new int[]{4};
    int[] initialSet2 = new int[]{1, 4, 5, 7, 8, 13, 15, 17, 19};
    int[] initialSet3 = new int[]{1, 4, 5, 7, 8, 13, 15, 17, 18, 19};

    Gaps g = new Gaps(initialSet3);
    GapIterator gapIterator = g.gapIterator();

    while (gapIterator.hasNext())
    {
      System.out.println(gapIterator.next());
    }

    int[] gaps = g.getGaps();
    for (int i: gaps)
    {
      System.out.println(i);
    }
  }
}
