import java.io.*;
import java.util.Random;

public class exam2 {
        public static void quickSort(int[] array, int low, int high) {
            if (array.length == 0)
                return;//завершить выполнение, если длина массива равна 0

            if (low >= high)
                return;//завершить выполнение если уже нечего делить

            // выбрать опорный элемент
            int middle = low + (high - low) / 2;
            int opora = array[middle];

            // разделить на подмассивы, который больше и меньше опорного элемента
            int i = low, j = high;
            while (i <= j) {
                while (array[i] < opora) {
                    i++;
                }

                while (array[j] > opora) {
                    j--;
                }

                if (i <= j) {//меняем местами
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    i++;
                    j--;
                }
            }

            // вызов рекурсии для сортировки левой и правой части
            if (low < j)
                quickSort(array, low, j);

            if (high > i)
                quickSort(array, i, high);
        }
    public static boolean isNumeric(String str) //этот метод проверяет, является ли введенная строка числом
    {
        try
        {
            int testI = Integer.parseInt(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Введите размер массива (целое число): ");
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String length = bufferedReader.readLine(); //получаем с клавиатуры размер массива
        boolean check = isNumeric(length);
        if (check) { //если введено число, создаем массив и начинаем заполнять его значениями
            int numLength = Integer.parseInt(length);
            int[] arr = new int[numLength];
            for (int i = 0; i < arr.length; i++) { //для каждого элемента массива
                if (i % 2 == 0) { //если элемент четный, значение задается рандомно
                    final Random random = new Random();
                    arr[i] = random.nextInt(100);
                    System.out.println("Элемент " + (i) + " - " + arr[i]);
                }
                else {
                    System.out.println("Введите элемент " + (i)); //если элемент нечетный, значение вводит пользователь
                    String num = bufferedReader.readLine();
                    check = isNumeric(num);
                    if (check) {
                        arr[i] = Integer.parseInt(num);
                    }
                    else {
                        System.out.println("Это не число!");
                        break;
                    }
                }
            }
            quickSort(arr, 0, arr.length-1); //когда ввод завершен, сортируем массив
            try (final FileWriter writer = new FileWriter("array.txt", false)) //После сортировки пытаемся сохранить в файл
            {
                for (int i = 0; i < arr.length; ++i)
                {
                    final String s = Integer.toString(arr[i]);
                    writer.write(s);
                    writer.write(System.lineSeparator());
                }
                System.out.println("Массив сохранен!"); //Если получилось сохранить, выводим сообщение об успехе
            }
            catch(IOException e) {
                System.out.println(e.getMessage()); //если не получилось сохранить, выводим сообщение с текстом ошибки
            }
        }
        else {
            System.out.println("Это не число!");
        }
    }
}
