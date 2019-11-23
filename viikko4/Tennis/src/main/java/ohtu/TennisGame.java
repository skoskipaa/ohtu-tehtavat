package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String score = "";

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1")) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {

        if (isEven()) {
            score = scoreIsEven(player1Score);
        } else if (isAdvantageOrWin()) {
            scoreIsAnvantageOrWin();
        } else {
            score = returnScore();
        }
        return score;
    }

    private String scoreIsEven(int player1score) {
        switch (player1Score) {
            case 0:
                return "Love-All";

            case 1:
                return "Fifteen-All";

            case 2:
                return "Thirty-All";

            case 3:
                return "Forty-All";

            default:
                return "Deuce";
        }
    }

    private void scoreIsAnvantageOrWin() {
        int scoreDifference = player1Score - player2Score;
        
        if (Math.abs(scoreDifference) >= 2) {
            isWin(scoreDifference);
        }
        isAdvantage(scoreDifference);
    }

    private void isAdvantage(int scoreDifference) {
        if (scoreDifference == 1) {
            score = "Advantage player1";
        } else if (scoreDifference == -1) {
            score = "Advantage player2";
        }
    }
    
    private void isWin(int scoreDifference) {
        if (scoreDifference >= 2) {
            score = "Win for player1";
        } else {
            score = "Win for player2";
        }
    }

    private String returnScore() {
        String score = "";
        int tempScore = 0;

        for (int i = 1; i < 3; i++) {
            if (i == 1) {
                tempScore = player1Score;
            } else {
                score += "-";
                tempScore = player2Score;
            }
            switch (tempScore) {
                case 0:
                    score += "Love";
                    break;
                case 1:
                    score += "Fifteen";
                    break;
                case 2:
                    score += "Thirty";
                    break;
                case 3:
                    score += "Forty";
                    break;
            }
        }
        return score;
    }

    public boolean isEven() {
        return (player1Score == player2Score);
    }

    public boolean isAdvantageOrWin() {
        return (player1Score >= 4 || player2Score >= 4);
    }
}
