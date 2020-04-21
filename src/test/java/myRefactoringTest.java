import org.junit.Test;
import org.openqa.selenium.By;
import pages.FormPage;
import pages.MainPage;
import pages.SecondPage;
import pages.TravelPage;

import static junit.framework.TestCase.assertTrue;

public class myRefactoringTest extends BaseTest {


    @Test
    public void newInsuranceTest() {
        driver.get(baseUrl);
        MainPage mainPage = new MainPage(driver);
        mainPage.selectMainMenu("Страхование");
        mainPage.selectSubMenu("Страхование путешественников");

/*      проверим, что у нас есть на странице текст "до 120 000 евро"
        assertEquals("до 120 000 евро",search_text.getText());
*/
        String text = "до 120 000 евро";
        String link = new SecondPage(driver).search_text.getText();
        assertTrue(text.equals(link));

        new SecondPage(driver).sendButton.click(); //Нажимаем "Оформить заявку" c SecondPage
        new TravelPage(driver).minS.click();//выбираем кнопку "Минимальная"
/*
        TravelPage travelPage = new TravelPage(driver);
        travelPage.selectMainGroup("Минимальная");
*/
        new TravelPage(driver).sendButtonTwo.click();//выбираем кнопку "Оформить"

        FormPage formPage = new FormPage(driver); //новый объект

        formPage.fillField("Фамилия /Surname", "Ivanov"); //с маленькой буквы!
        formPage.fillField("Имя / Given names", "Ruslan");
        formPage.fillField("Дата рождения/B", "23.03.2010");

        new FormPage(driver).str.click();

        formPage.fillField("Фамилия", "Васильев");
        formPage.fillField("Имя", "Михаил");
        formPage.fillField("Отчество", "Александрович");
        formPage.fillField("Дата рождения", "23.03.1986");

        new FormPage(driver).str.click();

        formPage.fillField("Серия паспорта", "3411");
        formPage.fillField("Номер паспорта", "341111");
        formPage.fillField("Кем выдан", "Т4444444444444");
        formPage.fillField("Дата выдачи", "03.10.2014");

        new FormPage(driver).phone.click();
        new FormPage(driver).card.click();

  //    новая проверка полей на корректность заполнения
        assertTrue("Ivanov".equals(formPage.getFillField( "Фамилия /Surname")));
        assertTrue("Ruslan".equals(formPage.getFillField( "Имя / Given names")));
        assertTrue("23.03.2010".equals(formPage.getFillField( "Дата рождения/B")));

        assertTrue("Васильев".equals(formPage.getFillField("Фамилия")));
        assertTrue("Михаил". equals(formPage.getFillField("Имя")));
        assertTrue("Александрович". equals(formPage.getFillField("Отчество")));
        assertTrue("23.03.1986". equals(formPage.getFillField("Дата рождения")));

        assertTrue("3411". equals(formPage.getFillField("Серия паспорта")));
        assertTrue("341111".equals(formPage.getFillField("Номер паспорта")));
        assertTrue("03.10.2014".equals(formPage.getFillField("Дата выдачи")));
        assertTrue("Т4444444444444". equals(formPage.getFillField("Кем выдан")));


  /* старая проверка полей на корректность заполнения
        assertTrue("Ruslan", driver.findElement(By.id("name_vzr_ins_0")).getAttribute("value"));
        assertTrue("23.03.2010", driver.findElement(By.id("birthDate_vzr_ins_0")).getAttribute("value"));

        assertTrue("Васильев", driver.findElement(By.id("person_lastName")).getAttribute("value"));
        assertTrue("Михаил", driver.findElement(By.id("person_firstName")).getAttribute("value"));
        assertTrue("Александрович", driver.findElement(By.id("person_middleName")).getAttribute("value"));

        assertTrue("3411", driver.findElement(By.id("passportSeries")).getAttribute("value"));
        assertTrue("341111", driver.findElement(By.id("passportNumber")).getAttribute("value"));
        assertTrue("03.10.2014", driver.findElement(By.id("documentDate")).getAttribute("value"));
        assertTrue("Т4444444444444", driver.findElement(By.id("documentIssue")).getAttribute("value"));
*/
        new FormPage(driver).next.click();

        String expected = "Поле не заполнено.";
        //String expected = "Введённые данные неверны.";

                String actual = driver.findElement(By.xpath("//span[contains(@class,'invalid-validate form-control__message')]")).getAttribute("innerText");
        //assertTrue(expected.equals(actual));
                assertTrue(String.format("Заголовок равен [%s]. Ожидалось - [%s]",
                        actual, expected), actual.contains(expected));


          }

}




