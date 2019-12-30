package broadridge;


public class Solution {
    int solution(int n) {
        int[] d = new int[30];
        int q = 0;
        int p;

        while (n > 0) {
            d[q] = n % 2;
            n /= 2;
            q++;
        }
        for (p = 1; p < 1 + q; p++) {
            int k;
            boolean ok = true;
            for (k = 0; k < q - p; k++) {
                if (d[k] != d[k + p]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return p;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.solution( 955 );
    }
}

/*
SELECT
  host_team,
  case when host_goals > guest_goals THEN 3
       when host_goals = guest_goals then 1
       else  0
  end,
  guest_team,
  case when host_goals > guest_goals THEN 3
       when host_goals = guest_goals then 1
       else  0
  end

FROM MATCHES

 */
/*

SELECT
  host_team,
  case when host_goals > guest_goals THEN 3
       when host_goals = guest_goals then 1
       else  0
  end
FROM MATCHES
UNION
SELECT
  host_team,
  case when guest_goals > host_goals THEN 3
       when guest_goals = host_goals then 1
       else  0
  end
FROM MATCHES
 */

/*
SELECT team, points FROM
(SELECT
  host_team as team,
  case when host_goals > guest_goals THEN 3
       when host_goals = guest_goals then 1
       else  0
  end as points
FROM MATCHES
UNION
SELECT
  guest_team as team,
  case when guest_goals > host_goals THEN 3
       when guest_goals = host_goals then 1
       else  0
  end as points
FROM MATCHES) AS ALL_POINTS


  SELECT team, points from (
    SELECT team, SUM(points) as points FROM
     (SELECT
       host_team as team,
       case when host_goals > guest_goals THEN 3
            when host_goals = guest_goals then 1
            else  0
       end as points
       FROM MATCHES
       UNION
       SELECT
         guest_team as team,
         case when guest_goals > host_goals THEN 3
              when guest_goals = host_goals then 1
              else  0
         end as points
       FROM MATCHES) AS ALL_POINTS
      GROUP BY TEAM) AS GROUPED
    ORDER BY points DESC

 */