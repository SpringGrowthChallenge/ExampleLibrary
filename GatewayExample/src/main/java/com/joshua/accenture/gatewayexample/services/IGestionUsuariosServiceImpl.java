package com.joshua.accenture.gatewayexample.services;

import com.joshua.accenture.gatewayexample.entities.Usuarios;
import com.joshua.accenture.gatewayexample.interfaces.IGestionUsuarios;
import com.joshua.accenture.gatewayexample.repositories.IUsuariosDao;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class IGestionUsuariosServiceImpl implements IGestionUsuarios {

    private IUsuariosDao usuariosDao;
    public IGestionUsuariosServiceImpl(IUsuariosDao usuariosDao) {
        this.usuariosDao = usuariosDao;
    }

    /**
     * @return
     */
    @Override
    public List<Usuarios> findAll() {
        return usuariosDao.findAll();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Optional<Usuarios> findById(Long id) {
        return usuariosDao.findById(id);
    }

    /**
     * @param usuario
     * @return
     */
    @Override
    public Usuarios save(Usuarios usuario) {
        return usuariosDao.save(usuario);
    }

    /**
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        usuariosDao.deleteById(id);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Optional<List<Usuarios>> findByNombre(String name) {
        return Optional.ofNullable(usuariosDao.findByNombre(name));
    }

    /**
     * @param email
     * @return
     */
    @Override
    public Optional<Usuarios> findByEmail(String email) {
        return usuariosDao.findByEmail(email);
    }

    /**
     * @param name
     * @param email
     * @return
     */
    @Override
    public Optional<Usuarios> findByNombreAndEmail(String name, String email) {
        return usuariosDao.findByNombreAndEmail(name, email);
    }

    /**
     * @param createdDate
     * @return
     */
    @Override
    public List<Usuarios> findByFechaCreacion(LocalDateTime createdDate) {
        return usuariosDao.findByFechaCreacion(createdDate);
    }
}
