package tum_model;

import core.Coord;
//import jdk.nashorn.internal.parser.Lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LectureRoom {
    private Coord position;
    private int capacity;
    private List<Lecture> lectures;

    public LectureRoom(Coord pos, int cap)
    {
        position = pos;
        capacity = cap;
        lectures = new ArrayList<>();
    }

    public Coord getPosition()
    {
        return position;
    }

    public int getCapacity()
    {
        return capacity;
    }


    public List<Lecture> getLectures()
    {
        return lectures;
    }

    // generates a random list of lectures for this room
    public void generateLectures(Random random, double lectureStart, double lectureDuration,
                                 double lectureEnd, double timeSlot)
    {
        double probOneHour = TumModelSettings.getInstance().getDouble(TumModelSettings.TUM_PROB_ONE_HOUR_LECTURE);
        double probTwoHour = TumModelSettings.getInstance().getDouble(TumModelSettings.TUM_PROB_TWO_HOUR_LECTURE);
        double probThreeHour = TumModelSettings.getInstance().getDouble(TumModelSettings.TUM_PROB_THREE_HOUR_LECTURE);

        double offset = timeSlot - lectureDuration;

        double  minutes1=15*60;
        double  minutes2=30*60;
        double  minutes3=45*60;

        double time = lectureStart;
        while (time < lectureEnd) {
            double result = random.nextDouble();
            if (result <= probOneHour && (lectureEnd - time) > lectureDuration) {
                double startOffset = random.nextInt((int)offset);
                double endOffset_max = minutes1 - startOffset;
                double endOffset= random.nextDouble()*endOffset_max;

                Lecture newLecture = new Lecture(time + startOffset, time + lectureDuration + endOffset, 1,this);
                lectures.add(newLecture);
                time += timeSlot;
            }
            else if (result <= probOneHour+probTwoHour && (lectureEnd - time) > lectureDuration*2 ) {
                double startOffset = random.nextInt(15);
                double endOffset_max = minutes2 - startOffset;
                double endOffset= random.nextDouble()*endOffset_max;
                Lecture newLecture = new Lecture(time + startOffset, time + lectureDuration*2 + endOffset, 2,this);
                lectures.add(newLecture);
                time += timeSlot * 2;
            }
            else if (result <= probOneHour+probTwoHour+probThreeHour && (lectureEnd - time) > lectureDuration*3) {
                double startOffset = random.nextInt(15);
                double endOffset_max = minutes3 - startOffset;
                double endOffset= random.nextDouble()*endOffset_max;
                Lecture newLecture = new Lecture(time + startOffset, time + lectureDuration*3 + endOffset, 3,this);
                lectures.add(newLecture);
                time += timeSlot * 3;
            }
            else {
                time += timeSlot;
            }
        }
    }

    public void printLectures() {
        for (Lecture l : lectures) {
            System.out.println("Lecture: " + l.getStartTime() + " - " + l.getEndTime());
        }
    }
}
