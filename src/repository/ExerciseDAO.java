package repository;

import model.Exercise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDAO {

    public static Exercise String;
    static List<Exercise> exercises = new ArrayList<>();

    public void save(Exercise exercise) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        try {
            if (exercise.getId() != null) {
                exerciseRepository.update(exercise);
            } else {
                exercise.setId(exerciseRepository.nextId());
                exerciseRepository.insert(exercise);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        exercises.add(exercise);
    }

    public void remove(Exercise exercise) throws Exception {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        exerciseRepository.delete(exercise);
    }

    public List<Exercise> searchAll() throws SQLException, ClassNotFoundException {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        try{
            exercises = exerciseRepository.search();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return exercises;
    }

    public List<Exercise> searchWithYear(Integer year) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        List<Exercise> filtredExercises = new ArrayList<>();

        try{
            exercises = exerciseRepository.searchByYear(year);

            for (Exercise exercise : exercises){
                filtredExercises.add(exercise);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return filtredExercises;
    }

    public Exercise searchById(Integer id) {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        Exercise exercise = new Exercise();
        try{
            exercises = exerciseRepository.searchById(id);

            for (Exercise auxExercise : exercises){
                if (auxExercise.getId().equals(id)) {
                    exercise = auxExercise;
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return exercise;
    }

    public List<Exercise> searchByYear(Integer year) {
        ExerciseRepository sectorRepository = new ExerciseRepository();
        List<Exercise> ListExercises = new ArrayList<>();

        try{
            exercises = sectorRepository.searchByYear(year);

            for (Exercise auxExercise : exercises){
                if (auxExercise.getYear().equals(year)) {
                    ListExercises.add(auxExercise);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ListExercises;
    }

    public Object[] searchAllOnlyWithYear() throws SQLException, ClassNotFoundException {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        ArrayList<Integer> years = new ArrayList<>();
        try{
            exercises = exerciseRepository.search();

            for (Exercise exercise : exercises) {
                years.add(exercise.getYear());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return years.toArray();
    }

    public Object[] searchAllWithIdOnYear() {
        ExerciseRepository exerciseRepository = new ExerciseRepository();
        List<String> exercisesYear = new ArrayList<>();

        try{
            exercises = exerciseRepository.search();

            for (Exercise exercise : exercises) {
                exercisesYear.add(exercise.getId() + " - " + exercise.getYear());
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return exercisesYear.toArray();
    }

    public Object[] searchAllReturnArray() throws SQLException, ClassNotFoundException {
        List<Exercise> exercises = searchAll();
        List<Integer> exercisesYears = new ArrayList<>();

        for (Exercise exercise : exercises) {
            exercisesYears.add(exercise.getYear());
        }

        return exercisesYears.toArray();
    }
}
