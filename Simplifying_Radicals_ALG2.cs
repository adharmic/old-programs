using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

public class SimpRad
{
        //Checks for digit values.
        static bool IsDigitsOnly(string str)
        {
            foreach (char c in str)
            {
                if (c < '0' || c > '9')
                    return false;
            }

            return true;
        }

        //Finds prime values using trial division.
        static bool isPrime(int number)
        {

            if (number == 1) return false;
            if (number == 2) return true;

            //Math.Ceiling rounds up values.
            for (int i = 2; i <= Math.Ceiling(Math.Sqrt(number)); ++i)
            {
                if (number % i == 0) return false;
            }

            return true;

        }

        static void Main(string[] args)
        {
            start:
            int num = 0;
            int t = 0;
            double r = 0;

            enterinput:
            Console.WriteLine("Enter your radical here:");
            Console.Write("√");

            //Exception handling here.
            string argh = Console.ReadLine();
            if (String.IsNullOrEmpty(argh))
            {
                Console.WriteLine("Please enter a value.");
                Console.Write("√");
                goto enterinput;
            }
            else if (argh == "0")
            {
                Console.WriteLine("Zeroes do not have a square root. Enter another value.");
                Console.Write("√");
                goto enterinput;
            }
            else if (argh.Contains("-"))
            {
                Console.WriteLine("Negative values cannot be square rooted. Enter another value.");
                Console.Write("√");
                goto enterinput;
            }
            else if (IsDigitsOnly(argh) == false)
            {
                Console.WriteLine("Do not enter non-numeric values.");
                Console.Write("√");
                goto enterinput;
            }
            else if (isPrime(int.Parse(argh)))
            {
                Console.WriteLine("Prime numbers cannot be simplified further. Enter another value.");
                Console.Write("√");
                goto enterinput;
            }

            //Main loop, finds largest perfect square and its complementary factor.
            int n = int.Parse(argh);
            double check = Math.Sqrt(n);
            if (Math.Sqrt(n) % 1 == 0)
            {
                Console.WriteLine("The value of √" + n + " is " + Math.Sqrt(n) + ".");

            }
            else
            {
                Console.Write("The factors are: 1");
                for (int x = 1; x <= n; x++)
                {
                    if (n % x == 0)
                    {
                        num = x;
                        if (x != 1)
                        {
                            Console.Write(", " + num);
                        }
                        if (Math.Sqrt(x) % 1 == 0)
                        {
                            r = Math.Sqrt(x);
                            t = n / x;
                        }
                    }
                }
                Console.WriteLine();
                Console.WriteLine("The simplest radical form of" + n + "is: " + r + "√" + t);
            }

            loopstart:
            Console.WriteLine("Would you like to restart the program? Enter yes or no.");
            string yesorno = Console.ReadLine();
            if (yesorno.Contains("y") || yesorno.Contains("Y"))
            {
                Console.Clear();
                goto start;
            }
            else if (yesorno.Contains("n") || yesorno.Contains("N"))
            {
                Environment.Exit(0);
            }
            else
            {
                Console.WriteLine("Invalid input value.");
                goto loopstart;
            }

        }
}


