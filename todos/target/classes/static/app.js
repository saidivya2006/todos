const apiBase = '/api/todos';
const todoList = document.getElementById('todo-list');
const titleInput = document.getElementById('title');
const descriptionInput = document.getElementById('description');
const addButton = document.getElementById('addButton');

const API_URL = "/api";
let todos = [];

async function loadTodos() {
  try {
    const response = await fetch(apiBase);
    todos = await response.json();
    renderTodos(todos);
  } catch (error) {
    todoList.innerHTML = '<div class="error">Unable to load todos.</div>';
    console.error(error);
  }
}

function renderTodos(todos) {
  if (!todos || todos.length === 0) {
    todoList.innerHTML = '<div class="empty">No tasks yet. Add one above.</div>';
    return;
  }
  todoList.innerHTML = todos.map(todo => {
    return `
      <div class="todo-item ${todo.completed ? 'completed' : ''}">
        <div class="todo-main">
          <div class="todo-title">${escapeHtml(todo.title)}</div>
          <div class="todo-description">${escapeHtml(todo.description)}</div>
        </div>
        <div class="todo-actions">
          <button class="toggle" data-id="${todo.id}">${todo.completed ? 'Undo' : 'Done'}</button>
          <button class="delete" data-id="${todo.id}">Delete</button>
        </div>
      </div>
    `;
  }).join('');

  document.querySelectorAll('.toggle').forEach(button => {
    button.addEventListener('click', async () => {
      const id = Number(button.dataset.id);
      const todo = todos.find(item => item.id === id);
      if (todo) {
        await updateTodo(id, { ...todo, completed: !todo.completed });
      }
    });
  });

  document.querySelectorAll('.delete').forEach(button => {
    button.addEventListener('click', async () => {
      const id = Number(button.dataset.id);
      await deleteTodo(id);
    });
  });
}

function escapeHtml(value) {
  return String(value || '').replace(/[&<>"'`]/g, s => ({
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": '&#39;',
    '`': '&#96;'
  })[s]);
}

async function addTodo() {
  const title = titleInput.value.trim();
  const description = descriptionInput.value.trim();
  if (!title) {
    titleInput.focus();
    return;
  }

  const todo = {
    id: Date.now(),
    title,
    description,
    completed: false
  };

  try {
    await fetch(apiBase, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(todo)
    });
    titleInput.value = '';
    descriptionInput.value = '';
    loadTodos();
  } catch (error) {
    console.error('Unable to add todo', error);
  }
}

async function updateTodo(id, todo) {
  try {
    await fetch(`${apiBase}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(todo)
    });
    loadTodos();
  } catch (error) {
    console.error('Unable to update todo', error);
  }
}

async function deleteTodo(id) {
  try {
    await fetch(`${apiBase}/${id}`, { method: 'DELETE' });
    loadTodos();
  } catch (error) {
    console.error('Unable to delete todo', error);
  }
}

addButton.addEventListener('click', addTodo);
window.addEventListener('DOMContentLoaded', loadTodos);
