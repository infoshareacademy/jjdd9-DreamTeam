package com.infoshareacademy.action.search;

import com.infoshareacademy.input.UserInputService;
import com.infoshareacademy.menu.Breadcrumbs;
import com.infoshareacademy.menu.item.BookListMenu;

import static com.infoshareacademy.menu.MenuUtils.STDOUT;
import static com.infoshareacademy.menu.MenuUtils.cleanTerminal;

public class Search {
    private UserInputService userInputService = new UserInputService();

    CriteriaChoice userCriteria = new CriteriaChoice();

    public void getSearchingCriteria() {
        do {
            showSearchPanel();
            switch (getUserChoice()) {
                case 1: {
                    userCriteria.setTitle();
                    break;
                }
                case 2: {
                    userCriteria.setAuthor();
                    break;
                }
                case 3: {
                    userCriteria.setAudio();
                    break;
                }
                case 4: {
                    Filtration.run(userCriteria);
                    Breadcrumbs.getInstance().addBreadcrumb(BookListMenu.SEARCH.getBookDescription());
                    break;
                }
                case 5: {
                    userCriteria.resetCriteria();
                    break;
                }
                case 0: {
                    Breadcrumbs.getInstance().removeBreadcrumb();
                    return;
                }
                default:
                    STDOUT.info("Proszę wpisać odpowiednią cyfrę.");
            }
        } while (true);
    }

    private void showSearchPanel() {
        cleanTerminal();
        STDOUT.info(Breadcrumbs.getInstance().displayBreadcrumb());
        STDOUT.info("WYSZUKIWANIE KSIĄŻEK \n\n");
        if (userCriteria.getActiveCriteria()[0]) {
            STDOUT.info("Tytuł: {} \n", userCriteria.getTitle());
        }
        if (userCriteria.getActiveCriteria()[1]) {
            STDOUT.info("Autor: {} \n", userCriteria.getAuthor());
        }
        if (userCriteria.getActiveCriteria()[2]) {
            STDOUT.info("Wersja audio: {} \n\n", userCriteria.getAudio() ? "tak" : "nie");
        }
    }

    public int getUserChoice() {
        STDOUT.info("Proszę wybrać kryterium wyszukiwania lub rozpocząć wyszukiwanie: \n");
        STDOUT.info("1. Tytuł \n");
        STDOUT.info("2. Imię autora \n");
        STDOUT.info("3. Dostępność wersji audio \n");
        STDOUT.info("4. Rozpoczęcie wyszukiwania \n");
        STDOUT.info("5. Reset kryteriów wyszukiwania \n");
        STDOUT.info("Wybierz 0 aby opuścić wyszukiwarkę książek \n");
        return userInputService.getUserInput();
    }
}
