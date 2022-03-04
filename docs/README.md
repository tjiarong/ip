# User Guide for Potaton
Your personal chatbot for task tracking.
## Features 
- Add tasks to be tracked
- Various task types (Event, Deadlines, ToDos)
- List and view all tasks
- Mark and unmark tasks
- Search for tasks
- Deletion of tasks

## Before Starting
1. Ensure that you have installed Java 11 or above.
2. Download latest Potaton.jar from [releases](https://github.com/tjiarong/ip/releases).
3. Copy the file to the folder you want to use as the _home folder_ for Potaton.
4. Open a terminal in that folder and launch Potaton by entering `java -jar Potaton.jar`.
5. Type your commands to Potaton and press Enter to execute.
6. Refer to the [Usage](#Usage) below for details of each command.

## Usage

### `todo` - Add a new Todo task

Adds a new task with an action.
The keyword `todo` is used with a short description of the task.

Format: `todo DESCRIPTION`

**Example usage:**

`todo Buy groceries`

**Expected outcome:**
```
_____________________________________________
Got it. I've added this task
[T][ ] Buy groceries
_____________________________________________
```

### `deadline` - Add a new Deadline task

Adds a new Deadline with a due date.
The keyword `deadline` is followed by a short description 
and a delimiter of `/by` followed by the due date in YYYY-MM-DD format.

Format: `deadline DESCRIPTION /by DUEDATE`

**Example usage:**

`deadline Assignment 1 /by 2022-03-28`

**Expected outcome:**
```
_____________________________________________
Got it. I've added this task
[D][ ] Assignment (by: Mar 28 2022)
_____________________________________________
```

### `event` - Add a new Event task

Adds a new Event with a start date.
The keyword `event` is followed by a short description
and a delimiter of `/at` followed by the due date in YYYY-MM-DD format.

Format: `event DESCRIPTION /at EVENTDATE`

**Example usage:**

`event Annual Party /at 2022-05-05`

**Expected outcome:**
```
_____________________________________________
Got it. I've added this task
[E][ ] Annual (at: May 5 2022)
_____________________________________________
```

### `list` - List out task list

Prints out the task list. Each entry shows the task index, task type,
its status and its description,

Format: `list`

**Example usage:**

`list`

**Expected outcome (task list from before):**
```
_____________________________________________
   Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Assignment (by: Mar 28 2022)
3. [E][ ] Annual (at: May 5 2022)
_____________________________________________
```

### `delete` - Deletes task

Deletes a specific task from the task list using its index.
The task is shown before removal.

Format: `delete INDEX`

**Example usage:**

`delete 2`

**Expected outcome (assuming same example from `list`):**
```
_____________________________________________
Noted. I've removed this task:
[D][ ] Assignment (by: Mar 28 2022)
_____________________________________________

```

### `find` - Search for task

Search for task matching a particular term. All matching
task will be displayed.

Format: `find TERM`

**Example usage:**

`find groceries`

**Expected outcome (assuming same example from `list`):**
```
_____________________________________________
Here are the matching tasks in your list:
[T][ ] Buy groceries
_____________________________________________
```

### `mark`/`unmark` - Mark or unmark a task as Done/Not Done

Mark or unmark a task as Done or Not Done using its list index.
Users should verify the task index using `list` before removal.

A character `X` denotes the task as Done.

Format: `mark INDEX / unmark INDEX`

**Example usage:**

`mark 1`

**Expected outcome (assuming same example from `list`):**
```
_____________________________________________
Nice! I've marked this task as done:
[T][X] Buy groceries
_____________________________________________
```

### `bye` - Exits the program

Get out of the chatbot

Format: `bye`

**Example usage:**

`bye`

**Expected outcome:**
```
_____________________________________________
Bye. Hope to see you again soon!
_____________________________________________
```
---

## FAQ
__Q__: How do I transfer my data to another Computer?

__A__: Download Potaton in the other computer and overwrite the empty data file it creates with the
file that contains the data of the Potaton home folder that you wish to import.

---
## Command Summary

- All dates are to be in YYYY-MM-DD format

| Action | Format                                                                                     |
|--------|--------------------------------------------------------------------------------------------|
| Add    | `todo TASK` <br/>`deadline DESCRIPTION /by DUEDATE` <br/>`event DESCRIPTION /at EVENTDATE` |
| List   | `list`                                                                                     |
| Delete | `delete INDEX`                                                                             |
| Find   | `find TERM`                                                                                |
| Mark   | `mark INDEX`<br/>`unmark INDEX`                                                            |
| Exit   | `bye`                                                                                      |